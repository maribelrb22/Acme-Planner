package acme.services;
import acme.entities.spam.Spam;
import acme.entities.spam.Word;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import acme.repositories.SpamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpamService implements AbstractListService<Anonymous, Spam> {

    @Autowired
    SpamRepository spam;

    @Override
    public boolean authorise(Request<Spam> request) {
        assert  request != null;
        return true;
    }

    @Override
    public void unbind(Request<Spam> request, Spam entity, Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "word");
    }
    public Double calculateComposedThreshold(String phrase, List<String> spamPhrases){
        double result = 0.;
        List<String> phraseSize = Arrays.stream(phrase.split(" ")).collect(Collectors.toList());
        for(String h: spamPhrases){
            String k = h.toLowerCase();
            if(phrase.contains(k)){
                List<String> wordsNumber = Arrays.stream(k.split(" ")).collect(Collectors.toList());
                result += (((double)wordsNumber.size()/(double)phraseSize.size())*100);
            }
        }
        return result;
    }
    public Double calculateSimpleThreshold(String phrase, List<String> spamWords){
        double result = 0.;
        List<String> wordList = new ArrayList<String>(Arrays.asList(phrase.split(" "))); // con esto tenemos todas las palabras de la frase dividida
        for(String a : wordList){ //empezamos a comparar una a una las palabras de la frase con las de la lista de spam
            String c = a.toLowerCase();
            for(String b: spamWords){
                String d = b.toLowerCase();
                if(c.equals(d)){ //si la palabra coincide con una de la lista de spam
                    result += ((1./wordList.size())*100); //le sumamos al threshold su tanto % correspondiente conforme a la longitud de la frase
                }else {
                    result += 0.;
                }
            }
        }
        return result;
    }

    public boolean isItSpam(String frase){
        String phrase = frase.toLowerCase().trim();
        boolean result;
        double thresholdWords;
        double thresholdPhrases;
        double threshold;
        List<String> spamPhrases = new ArrayList<>();
        List<String> spamWords = new ArrayList<>();
        List<String> spamAll = spam.findOne().getSpamWords().stream().map(Word::getWord).collect(Collectors.toList());
        double required = spam.findOne().getThreshold();
        for(String s: spamAll){ //con esto dividimos las palabras de las frases hechas de spam
            String t = s.trim(); //eliminamos los espacios del principio y del final para ver si realmente es una frase hecha
            if(t.contains(" ")){ //si tiene espacios intermedios es una frase hecha
                spamPhrases.add(t);
            }else{ //si no sabemos que es solo una palabra
                spamWords.add(t);
            }
        }
        thresholdPhrases =  calculateComposedThreshold(phrase, spamPhrases);
        thresholdWords = calculateSimpleThreshold(phrase, spamWords);
        threshold = thresholdPhrases + thresholdWords;
        if(threshold<=required){
            result = false;
        }else{
            result= true;
        }
        return result;
    }



    @Override
    public Collection<Spam> findMany(Request<Spam> request) {
        //assert request != null;
        Collection<Spam> result=null;
       // result = this.spam.findOne();
        return result;
    }
}
