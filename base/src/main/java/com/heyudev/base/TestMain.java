package com.heyudev.base;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMain {
    @lombok.SneakyThrows
    public static void main(String[] args) {
//        LocalDateTime lastMin = LocalDateTime.now().minusMinutes(1);
//        System.out.println(lastMin);
//        lastMin = lastMin.withSecond(0).withNano(0);
//        System.out.println(lastMin);
//        LocalDateTime startMin = lastMin.minusDays(1);
//        System.out.println(startMin);
//        // to millisecond
//        long startTimestamp = startMin.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        long endTimestamp = lastMin.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        System.out.println(startTimestamp);
//        System.out.println(endTimestamp);

        // 生成一个0到1之间的随机数
//        double randomValue = Math.random();
//        LOGGER.info("waitRecommend limit randomValue {}", randomValue);
        // 检查随机数是否小于或等于概率值
//        return randomValue <= probability;
//        System.out.println(randomValue);
//        int count = 0;
//        int count1 = 0;
//        for (int i = 0; i < 100; i++) {
//            double randomValue = Math.random();
//            if (randomValue > 0.5) {
//                count++;
//            } else {
//                count1++;
//            }
//            System.out.println(Math.random());
//        }
//        System.out.println(count + "--" + count1);


//        String s = "标题因子的维度为：\n" +
//                "一、涉及老年人关心话题：经济安全、生活幸福、情感联系、幽默娱乐、怀旧情怀、实用信息、文化欣赏、社会关注 二、情感诉求： 这些标题通常包含情感上的诉求，如\"亲爱的老同学，多聚聚吧！！\"，这种直接的呼唤能够引起共鸣，激发用户的情感反应。 \n" +
//                "三、群体归属感： 标题经常直接指向特定的群体，比如提到“退休”“老人”“老同学”，如\"\uD83D\uDD34致全体老人的一段话，太美了，请朋友们收好！\"，这种直接的称呼能够增强群体归属感。 \n" +
//                "四、缺乏互动性： 如果标题没有鼓励用户参与或互动，可能会降低用户的参与度，如“\uD83D\uDC49老人临终前最担心的是什么你知道吗”，这种提问方式可能需要更多的背景信息来吸引用户。\n" +
//                "五、社会关注和参与感 特征：涉及社会事件或强调集体参与感的标题能增加用户的点击和分享欲望。\n" +
//                "六、表达方式：标题多使用直接、亲切的语言，易于老年人理解和接受。 \n" +
//                "七、安全性：不包含以下任意特征： 涉及政治；国家领导人；官方机构、媒体；国内政策；贪腐话题；国际新闻；转基因话题；煽动民族仇恨；虚假社会事件；食品安全问题；恶搞政治人物；抨击体制单位；冒用名人；虚假伤亡事故；虚假迷信内容；涉政标题党；过度引导分享；迷信祝福；急救偏方；乱用药物；影响寿命；食物相克；夸大食物效果；夸大正能量；夸大黑科技；评论时政；具有谣言成分，如使用夸大、诱惑、违背客观事实的文字；恶意煽动、混淆、误导；\n" +
//                "请学习标题因子维度，对以下标题在各个标题因子维度，做评分，分数范围为1-10分。越符合标题因子的描述，分数评分越高。在原标题基础上生成5个新标题，如果原标题的标题因子分低于6分，那么请修改标题使其新标题每个标题因子分大于6分。其中，“安全性”满足任意特征则 “安全性”低过5分，满足越多，分数越低。最终按中老年的喜好程度排序，输出为每个原标题新生成的10个标题：\n" +
//                "标题为：\n" +
//                "%s\n" +
//                "不要包含任何解释，只提供符合RFC8259标准的JSON String响应结果，严格遵循此格式，不得偏离；其中key为原标题，原标题和输入要完全一致，value为新标题集合";
//        System.out.println(s);

//        List<List<Integer>> list = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(2);
//        list.add(list1);
//        System.out.println(list.contains(list1));
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(2);
////        list2.add(1);
//        System.out.println(list.contains(list2));
//
//        System.out.println(Objects.equals(list2, list1));
//
//
//        List<List<String>> list3 = new ArrayList<>();
//        List<String> list4 = new ArrayList<>();
//        list4.add(1 + "");
//        list4.add(2 + "");
//        list3.add(list4);
////        System.out.println(list3.contains(list4));
//        List<String> list5 = new ArrayList<>();
//        list5.add(1 + "");
//        list5.add(2 + "");
////        list2.add(1);
//        System.out.println(list3.contains(list5));
//        list4.equals(list5);
//        System.out.println(Objects.equals(list4, list5));
//
//        String s = "标题因子的维度为：\n一、涉及老年人关心话题：经济安全、生活幸福、情感联系、幽默娱乐、怀旧情怀、实用信息、文化欣赏、社会关注 二、情感诉求： 这些标题通常包含情感上的诉求，如\"亲爱的老同学，多聚聚吧！！\"，这种直接的呼唤能够引起共鸣，激发用户的情感反应。 \n" +
//                "三、群体归属感： 标题经常直接指向特定的群体，比如提到“退休”“老人”“老同学”，如\"\uD83D\uDD34致全体老人的一段话，太美了，请朋友们收好！\"，这种直接的称呼能够增强群体归属感。 \n" +
//                "四、缺乏互动性： 如果标题没有鼓励用户参与或互动，可能会降低用户的参与度，如“\uD83D\uDC49老人临终前最担心的是什么你知道吗”，这种提问方式可能需要更多的背景信息来吸引用户。\n" +
//                "五、社会关注和参与感 特征：涉及社会事件或强调集体参与感的标题能增加用户的点击和分享欲望。\n" +
//                "六、表达方式：标题多使用直接、亲切的语言，易于老年人理解和接受。 \n" +
//                "七、安全性：不包含以下任意特征： 涉及政治；国家领导人；官方机构、媒体；国内政策；贪腐话题；国际新闻；转基因话题；煽动民族仇恨；虚假社会事件；食品安全问题；恶搞政治人物；抨击体制单位；冒用名人；虚假伤亡事故；虚假迷信内容；涉政标题党；过度引导分享；迷信祝福；急救偏方；乱用药物；影响寿命；食物相克；夸大食物效果；夸大正能量；夸大黑科技；评论时政；具有谣言成分，如使用夸大、诱惑、违背客观事实的文字；恶意煽动、混淆、误导；\n" +
//                "请学习标题因子维度，对以下标题在各个标题因子维度，做评分，分数范围为1-10分。越符合标题因子的描述，分数评分越高。在原标题基础上生成10个新标题，如果原标题的标题因子分低于6分，那么请修改标题使其新标题每个标题因子分大于6分。其中，“安全性”满足任意特征则 “安全性”低过5分，满足越多，分数越低。最终按中老年的喜好程度排序，输出为每个原标题新生成的10个标题：\n" +
//                "标题为：\n" +
//                "%s\n" +
//                "要求输出格式为符合RFC8259标准的JSON格式的字符串，其中key为原标题，原标题和输入要完全一致，value为新标题集合；注意：不要以json代码块形式返回。";

//        System.out.println(a("abc3[cd]xyz"));


//        do {
//            TimeUnit.SECONDS.sleep(2);
////            if (true) {
////                System.out.println(1);
////                continue;
////            }
//            if (true) {
//                System.out.println(2);
//                return;
//            }
//
//            System.out.println(3);
//        }while (true);

        String s = "123";
        System.out.println();

    }

    public static String a(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack();
        StringBuilder currentString = new StringBuilder();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '[') {
                numStack.push(num);
                strStack.push(currentString);
                num = 0;
                currentString = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder decodestring1 = strStack.pop();
                int repeatTimes = numStack.pop();
                for (int j = 0; j < repeatTimes; j++) {
                    decodestring1.append(currentString);
                }
                currentString = decodestring1;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }

}

