package com.aashish.springboot.firstspringbootrestapi.survey;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SurveyResource {

	private SurveyService surveyService;

	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys() {
		return surveyService.retrieveAllSurveys();
	}

	@RequestMapping("/surveys/{surveyId}")
	public Survey retrieveSurvey(@PathVariable String surveyId) {
		Survey survey = surveyService.retrieveSurvey(surveyId);

		if (survey == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return survey;
	}

	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveSurveyQuestions(@PathVariable String surveyId) {
		List<Question> questions = surveyService.retrieveSurveyQuestions(surveyId);

		if (questions == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return questions;
	}

	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		Question question = surveyService.retrieveSurveyQuestion(surveyId, questionId);

		if (question == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return question;
	}

	@RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
	public ResponseEntity<Object> addSurveyQuestion(@PathVariable String surveyId, @RequestBody Question question) {
		surveyService.addSurveyQuestion(surveyId, question);

		return ResponseEntity.created(null).build();

	}

	@RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public Question deleteSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		Question question = surveyService.deleteSurveyQuestion(surveyId, questionId);

		if (question == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return question;
	}

	@RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
	public Question updateSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId,
			@RequestBody Question question) {
		surveyService.updateSurveyQuestion(surveyId, questionId, question);

		if (question == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return question;
	}

}