package api.vis.user.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import api.vis.notificationserver.feignclient.SmsFeignClient;
import api.vis.notificationserver.model.form.SmsForm;
import api.vis.notificationserver.model.response.SmsVO;
import api.vis.user.model.Usuario;
import api.vis.user.model.Verificacao;
import api.vis.user.model.form.UsuarioForm;
import api.vis.user.model.response.UsuarioDetailsVO;
import api.vis.user.repository.UsuarioRepository;
import api.vis.user.repository.VerificacaoRepository;
import api.vis.util.VisAquaeConstantes.Entity;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private VerificacaoRepository verificacaoRepository;
	private SmsFeignClient notificationFeignClient;
	BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, VerificacaoRepository verificacaoRepository,
			SmsFeignClient notification) {
		this.usuarioRepository = usuarioRepository;
		this.verificacaoRepository = verificacaoRepository;
		this.notificationFeignClient = notification;
	}

	public Usuario findById(Long id) {

		return usuarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException(Entity.USUARIO));
	}

	public Usuario findByTelefone(String telefone) {

		return usuarioRepository.findByTelefone(telefone).orElseThrow(() -> new NoSuchElementException(Entity.USUARIO));
	}

	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<?> register(UsuarioForm form) {

		List<Usuario> usuarios = usuarioRepository.customFindByCpfEmailOrTel(form.getCpf(), form.getEmail(),
				form.getTelefone());

		if (!usuarios.isEmpty()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os dados informados já foram cadastrados");

		} else {
			Verificacao verificacao = verificacaoRepository.save(new Verificacao());

			Usuario usuario = usuarioRepository.save(new Usuario(form, bCrypt, verificacao));

			SmsVO sms = notificationFeignClient
					.smsSend(new SmsForm(usuario.getTelefone(), new Integer(usuario.getVerificacao().getCodigo())));

			return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDetailsVO(usuario));
		}
	}

	public Boolean activeUser(Long id) {

		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException(Entity.USUARIO));

		if (usuario.getVerificacao().getId() != null) {

			usuario.setFlagAtivo(true);

			Verificacao verificacao = verificacaoRepository.findById(usuario.getVerificacao().getId())
					.orElseThrow(() -> new NoSuchElementException("Verificacao"));

			usuario.setVerificacao(null);

			verificacaoRepository.delete(verificacao);

			usuarioRepository.save(usuario);

			return true;
		}
		return false;
	}
}