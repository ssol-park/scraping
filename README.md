## Jsoup(https://jsoup.org)
### Jsoup 이란?
- 실제 HTML 및 XML을 다루는 작업을 단순화하는 Java 라이브러리
- URL 가져오기, 데이터 파싱, 추출 및 조작을 위한 사용하기 쉬운 API를 제공
- DOM API 메서드, CSS 선택자 및 XPath 선택자를 사용하여 작업 수행 가능

### example
```java
// 세팅된 URL의 HTML을 가져오고 파싱하여 DOM 객체 생성
Document doc = Jsoup.connect("https://example.com").get();

// DOM 의 가장 첫번째 tag 선택
Element table = doc.selectFirst("table");
Element table = doc.selectFirst("table[summary='abc']");

// ID로 요소 선택
Element elementById = doc.getElementById("exampleId");

// 클래스 이름으로 요소 선택
Elements elementsByClass = doc.getElementsByClass("exampleClass");

// 속성 값으로 요소 선택
Elements elementsByAttribute = doc.getElementsByAttribute("exampleAttr");            
            
// 특정 속성 값으로 요소 선택
Elements elementsByAttributeValue = doc.getElementsByAttributeValue("exampleAttr", "value");            
            
// CSS 선택자로 요소 선택
Element elementByCssSelector = doc.selectFirst("div.exampleClass");            
            
// 태그 이름으로 요소 선택            
Elements elementsByTag = doc.getElementsByTag("p");

```
