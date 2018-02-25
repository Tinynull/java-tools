package info.zhaoliang.stanfordnlp;

import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations;
import edu.stanford.nlp.ie.machinereading.structure.RelationMention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhaoliang on 2017/9/6.
 */
public class BasicPipelineExample {
    public static void main(String[] args) {

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,relation");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = "you can give other properties to CoreNLP by build a Properties object with more stuff in it.";
        String text2 = "交通违章现场处罚决定书只是一个告知单，丢了影响不大。可以到交警大队处罚中心，把行驶证、驾驶证交给他们，他们会在电脑上打出违章记录，并开具正式罚款收据的";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text2);

        // run all Annotators on this text
        pipeline.annotate(document);

        interpretingOutput(document);

    }

    public static void interpretingOutput(Annotation document) {
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        List<RelationMention> relationMentions = document.get(MachineReadingAnnotations.RelationMentionsAnnotation.class);
        relationMentions.forEach(System.out::println);
        System.out.println("------");

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods


            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                String ner = token.get(CoreAnnotations.NERIDAnnotation.class);
                List<CoreMap> coreMaps = token.get(CoreAnnotations.MentionsAnnotation.class);

                System.out.println(String.format("word : %s \n pos : %s \n ne : %s \n ner : %s \n", word, pos, ne, ner));
            }

            // this is the parse tree of the current sentence
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);


            // this is the Stanford dependency graph of the current sentence
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
        }

// This is the coreference link graph
// Each chain stores a set of mentions that link to each other,
// along with a method for getting the most representative mention
// Both sentence and token offsets start at 1!
        Map<Integer, CorefChain> graph =
                document.get(CorefCoreAnnotations.CorefChainAnnotation.class);
    }
}
