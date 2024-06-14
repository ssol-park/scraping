package com.study.scraping;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Scraping {
    public static void main(String[] args) {
        String domain = "https://www.38.co.kr";
        String baseUrl = String.format("%s/html/fund/?o=k", domain);

        try {

            // TODO :: 스크래핑 가능 범위 확인
            String ScrapScope = getScrapScope("https://www.38.co.kr");

            Document doc = Jsoup.connect(baseUrl).get();

            // 테이블 요소 선택
//            Element table = Optional.ofNullable(doc.selectFirst("table[summary='공모주 청약일정']")).orElseThrow(() -> new RuntimeException("empty table"));
//            Element tbody = Optional.ofNullable(table.selectFirst("tbody")).orElseThrow(() -> new RuntimeException("empty tbody"));
//            Elements trs = tbody.select("tr");
//            Elements a = trs.select("a");
//
//            int idx = 0;
//            for (Element element : a) {
//                String link = String.format("%s%s", domain, element.attr("href"));
//
//                if(idx == 0) {
//                    Document document = Jsoup.connect(link).get();
//                    Element detail = Optional.ofNullable(document.selectFirst("table[summary='공모정보']")).orElseThrow(() -> new RuntimeException("empty table"));
//                    log.info("{}", detail);
//                }
//
//                idx++;
//            }

//            for (Element tr : trs) {
//                log.info("{}", Jsoup.clean(tr.selectFirst("a").toString(), customSafelist));
//                log.info("================================");
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO :: 리턴 타입 정의
    private static String getScrapScope(String domain) throws IOException {
        List<String> disallows = new ArrayList<>();

        Element body = Jsoup.connect(String.format("%s/robots.txt", domain)).get().body();

        String[] tokens = body.outerHtml().split(" ");
        int tokenLength = tokens.length;

        if(tokenLength == 0) {
            throw new RuntimeException(String.format("failed to get scope. token length :: %d", tokenLength));
        }

        for (int i = 0; i < tokenLength; i++) {
            if(tokens[i].equals("Disallow:")) {
                disallows.add(tokens[i + 1]);
                i++;
            }
        }

        if(disallows.size() == 0)
            return "ALL";
        else if(disallows.size() == 1 && disallows.contains("/"))
            return "DENY";
        else
            return "PART";
    }
}
