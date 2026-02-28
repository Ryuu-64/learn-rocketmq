sh mqadmin updatetopic \
-n rmqnamesrv:9876 \
-b 172.18.0.3:10911 \
-t TestTopic

#创建完毕后会出现如下的信息：
#create topic to 172.18.0.3:10911 success.
#TopicConfig [topicName=TestTopic, readQueueNums=8, writeQueueNums=8, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false, attributes={}]
