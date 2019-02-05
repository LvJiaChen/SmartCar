# SmartCar
## 主要实现的功能
* 商品导航：
此模块主要功能是基于指纹WiFi室内定位，对人进入超市后位置进行实时定位，确定人所在位置，每个商品也具有位置标签，这样就可以确定人与商品的相对位置信息，最终实现商品导航。
* 商品价格扫描：
通过Android手机摄像头对商品进行条形码扫描，得到条形码编号，再通过数据库查找出编号所对应的商品的信息。然后存入Android手机自带的SQLite数据库中，最后把SQLite中的所有数据中的价格进行合计，算出总消费额。
* 在线支付宝支付功能：
通过支付宝提供的沙箱模拟支付接口，模拟进行商品在线支付，是减少用户排队时间。
## 定位算法的实现
<br> 离线数据采集阶段，本文使用智能手机终端采集了4个在4个样本点的RSSI样本数据进行分析，本次测试用到的WiFi环境为宁夏理工学院信息中心B106，教室内一共如图所示部署4个无线AP的RSSI，采样设备为魅族not1，采样时间为40秒，采样总个数为4个。</br>
<br>在离线数据采集阶段，设移动终端在某个参考点(X, Y)经过一段时间的采样采集到的RSSI={ rssil , rssi2, ..., rssin }和标准方差值，将样本数据集的均值及位置信息存入数据库中作为该点的指纹数据记为(x, y, ).当所有参考点的RSSI数据采集完之后，可以得到一个完整的指纹数据库。</br>
<br>基于概率的方法，是一种基于贝叶斯定理的定位算法。在实时定位阶段，在某个位置采集到的RSSI样本数据为RSSI={ rssil , rssi2, ..., rssin }基于概率的方法就是，通过贝叶斯定理推算出实时的RSSI在指纹数据库中的己知位置的后验p(L|RSSI)。具体推理过程如下（5-1）:</br>
![](https://github.com/LvJiaChen/SmartCar/blob/master/images/bys.png)
