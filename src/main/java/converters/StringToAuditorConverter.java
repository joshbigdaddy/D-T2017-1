package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditorRepository;
import domain.Auditor;

@Component
@Transactional
public class StringToAuditorConverter implements Converter<String, Auditor> {

	@Autowired
	AuditorRepository auditorRepository;

	public Auditor convert(String text) {
		Auditor result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = auditorRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
