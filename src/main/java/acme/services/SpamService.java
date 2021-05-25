package acme.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.WordClass;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import acme.repositories.SpamRepository;

@Service
public class SpamService implements AbstractListService<Anonymous, Spam> {

	@Autowired
	SpamRepository spam;

	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "word");
	}

	public Double calculateComposedThreshold(final String phrase, final List<String> spamPhrases) {
		double result = 0.;
		final List<String> phraseSize = Arrays.stream(phrase.split(" ")).collect(Collectors.toList());
		for (final String h : spamPhrases) {
			final String k = h.toLowerCase();
			if (phrase.contains(k)) {
				final List<String> wordsNumber = Arrays.stream(k.split(" ")).collect(Collectors.toList());
				result += (((double) wordsNumber.size() / (double) phraseSize.size()) * 100);
			}
		}
		return result;
	}

	public Double calculateSimpleThreshold(final String phrase, final List<String> spamWords) {
		double result = 0.;
		final List<String> wordList = new ArrayList<String>(Arrays.asList(phrase.split(" "))); // con esto tenemos todas
																								// las palabras de la
																								// frase dividida
		for (final String a : wordList) { // empezamos a comparar una a una las palabras de la frase con las de la lista
											// de spam
			final String c = a.toLowerCase();
			for (final String b : spamWords) {
				final String d = b.toLowerCase();
				if (c.equals(d)) { // si la palabra coincide con una de la lista de spam
					result += ((1. / wordList.size()) * 100); // le sumamos al threshold su tanto % correspondiente
																// conforme a la longitud de la frase
				} else {
					result += 0.;
				}
			}
		}
		return result;
	}

	public boolean isItSpam(final String frase) {
		final String phrase = frase.toLowerCase().trim();
		boolean result;
		double thresholdWords;
		double thresholdPhrases;
		double threshold;
		final List<String> spamPhrases = new ArrayList<>();
		final List<String> spamWords = new ArrayList<>();
		final List<String> spamAll = this.spam.findOne().getSpamWords().stream().map(WordClass::getWord)
				.collect(Collectors.toList());
		final double required = this.spam.findOne().getThreshold();
		for (final String s : spamAll) { // con esto dividimos las palabras de las frases hechas de spam
			final String t = s.trim(); // eliminamos los espacios del principio y del final para ver si realmente es
										// una frase hecha
			if (t.contains(" ")) { // si tiene espacios intermedios es una frase hecha
				spamPhrases.add(t);
			} else { // si no sabemos que es solo una palabra
				spamWords.add(t);
			}
		}
		thresholdPhrases = this.calculateComposedThreshold(phrase, spamPhrases);
		thresholdWords = this.calculateSimpleThreshold(phrase, spamWords);
		threshold = thresholdPhrases + thresholdWords;
		if (threshold <= required) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	public boolean isASpamWord(final String frase) {
		final String spamWord = frase.toLowerCase().trim();
		final List<String> spamAll = this.spam.findOne().getSpamWords().stream().map(WordClass::getWord)
				.collect(Collectors.toList());
		final List<String> allMinus = new ArrayList<>();
		for (final String word : spamAll) {
			final String m = word.toLowerCase();
			allMinus.add(m);
		}
		return allMinus.contains(spamWord);
	}

	@Override
	public Collection<Spam> findMany(final Request<Spam> request) {
		// assert request != null;
		// final Collection<Spam> result=null;
		// result = this.spam.findOne();
		return Collections.emptyList();
	}
}
