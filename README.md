# JaySpringCoreHomework

<b>題目
2021/12/19-
請使用 Java 8 stream 進行資料分析並取得 mary 的老師有誰? (印出 name)</b>

程式碼: [homework1](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/test/java/com/study/springcore/case05/Test1.java)

<b>題目2021/12/26 - 
完成 JsonDB.java 如果 person 已存在則 return false (name、age、birth 皆與目前資料庫某一 person 資料相同)</b>

程式碼:[homework2](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/case08/JsonDB.java)

<b>完成 PersonSystem.java 選項3 ~ 7資料分析與處理</b>

程式碼: [homework3](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/case08/PersonController.java)

<b>題目2022-01-09 -
在每次的查詢 queryAll()時 都可以將 查詢時間的 Log 記錄下來</b>

程式碼:
       [homework4-1->建立資料表](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/jdbc/sql/20220109HomeWork.sql)/
       [homework4-2->建Aspect類別RecordQuery](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/jdbc/template/RecordQuery.java)/
      [homework4-3->在conf/jdbc-config.xml中配置Aspect](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/conf/jdbc-config.xml)
     

<b>題目2022/01/16 -依講義Spring jdbc Template第34~36頁實作lab分析</b><br>
1.每一張發票有那些商品?<br>
2.每一張發票有幾件商品?<br>
3.每一張發票價值多少?<br>
4.每一樣商品各賣了多少?<br>
5.哪一件商品賣得錢最多?<br>
6.哪一張發票價值最高（請練習看看）?

程式碼:[homework5](https://github.com/ugug1314/JaySpringCoreHomework/tree/master/src/main/java/com/study/springcore/homework0116)


<b>題目2022/01/23 -
於購買書藉時，紀錄購買的資訊</b><br>
程式碼:[homework6-1->建立sql資料表](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/homework0116/sql/ceatesql_and_analyze.sql)/
      [homework6-2->建立Aspect類別BuyingRecord](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/tx/dao/BuyingRecord.java)/
      [homework6-3->BookDao中新增setrecord及getbuyingrecord方法](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/tx/dao/BookDao.java)/
      [homework6-4->BookDaoImpl中新增實作](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/src/main/java/com/study/springcore/tx/dao/BookDaoImpl.java)/
      [homework6-5->在conf/jdbc-config.xml中配置Aspect](https://github.com/ugug1314/JaySpringCoreHomework/blob/master/conf/jdbc-config.xml)
