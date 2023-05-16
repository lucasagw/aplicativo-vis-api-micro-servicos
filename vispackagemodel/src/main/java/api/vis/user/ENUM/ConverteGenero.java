package api.vis.user.ENUM;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConverteGenero implements AttributeConverter<GeneroEnum, String> {

	@Override
	public String convertToDatabaseColumn(GeneroEnum attribute) {

		return attribute == null ? null : attribute.getCodigo();
	}

	@Override
	public GeneroEnum convertToEntityAttribute(String dbData) {

		return dbData == null ? null : GeneroEnum.valueOfCodigo(dbData);
	}
}
