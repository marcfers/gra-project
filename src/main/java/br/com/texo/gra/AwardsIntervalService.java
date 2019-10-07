package br.com.texo.gra;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/awardsInterval")
public final class AwardsIntervalService {

	private final AwardsIntervalController controller;

	AwardsIntervalService(final AwardsIntervalController controller) {
		this.controller = controller;
	}

	@RequestMapping("/betweenTwoAwards")
	public AwardsIntervalDTO getBetweenTwoAwards() {
		return new AwardsIntervalDTO(this.controller.getMinProducers(), this.controller.getMaxProducers());
	}
}
