package com.tencent.wxcloudrun.dao;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.apdplat.word.segmentation.Word;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


public class WordTest {
    @Test
    void wordTry() {
        String text1 = "珺珺子我好喜欢你啊";
        String text2 = "王珺哥哥我好喜欢你啊";


        TextSimilarity textSimilarity = new CosineTextSimilarity();
        double score1 = textSimilarity.similarScore(text1, text2);

        System.out.println(score1);
    }
}
