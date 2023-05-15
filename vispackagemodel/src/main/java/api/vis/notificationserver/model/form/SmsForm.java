package api.vis.notificationserver.model.form;

public class SmsForm {
	
	private String key = "UN5LXMCMCZ998HSDXO1N1UKPMFVCBYN2080I0FCSKDK9D4R22IE3UZ2H20Y8LZOD79R8X55I1LXFYBSPX9G6YPK39YQMF16SNBIA9XK43CITBU6ZAFEKP9GH8IUC7CAO";
	private String number;
	private String Template = "Bem-vindo ao vis. Seu código de verificação é: {999-999}";
	private int code;
	
	public SmsForm(String number, int code) {
		this.number = number;
		this.code = code;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTemplate() {
		return Template;
	}
	public void setTemplate(String template) {
		Template = template;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	

}
