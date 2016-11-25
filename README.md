# SegPaper

## 词表、词典构造

1. 从论文中抽取关键词，通过SparkTagPaper中的WordCount去重并统计词频。抽取词频>=10并且长度在[2,5]之间的关键词。

2. 对1中剩下的部分，使用SegPaper中的SegPhrase抽取phrase。

3. 将1中抽取的部分和2中抽取到的phrase合并为词典。

4. 用词典对所有的关键词进行分词，去重后为词表。


## 分词

使用构造的词典，放在路径library/default目录下，使用SegPaper中SegContent进行分词。


## link

[SparkTagPaper](https://github.com/BUAAQingYuan/SparkTagPaper)

[TagPaper](https://github.com/BUAAQingYuan/TagPaper)
