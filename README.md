# SegPaper

## 词表、词典构造

1. 从论文中抽取关键词，通过SparkTagPaper中的WordCount去重并统计词频。

2. 使用SegPaper中的ExtractPhrase抽取phrase。

3. 对抽取到的phrases，再次使用WordCount去重并统计词频，生成词表。

4. 对词表使用TagPaper中的GenerateDic，生成词典。


## 分词

使用构造的词典，放在路径library/default目录下，使用SegPaper中SegContent进行分词。


## link

[SparkTagPaper](https://github.com/BUAAQingYuan/SparkTagPaper)

[TagPaper](https://github.com/BUAAQingYuan/TagPaper)
