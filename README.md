# KHostAdmin

使用Kotlin 编写的Host管理工具

虽然Chrome插件可以切换，但是由于一个项目的启动可能依赖其他项目，相应的IP地址也可能需要配置多个

为了方便批量切换，产生profile概念，先将host进行环境分类，再通过选中与否进行批量更换

同时，只在应用启动时host生效，应用关闭时即恢复初始状态，如此避免host混乱，可以在原host文件中，只写入常用的必须的host配置，而在profile加入临时的配置


### 使用说明
主界面如下
![khost1](https://user-images.githubusercontent.com/2569600/34864402-0d82b2c8-f7af-11e7-822a-11274fbf39e7.png)

左侧为profile列表，右侧为host列表，默认为空
应用启动时，将在用户目录下的.khost文件夹中，生成一个host文件的备份，并在关闭时还原

点击Add Profile出现如下图的对话框

![khost3](https://user-images.githubusercontent.com/2569600/34864406-123e0dee-f7af-11e7-96cc-124acf08ff0e.png)

填入Profile名称后即可在左侧点击，并在右侧点击AddHost可出现如下对话框

![khost2](https://user-images.githubusercontent.com/2569600/34864404-0db5a3ea-f7af-11e7-93bd-a2aeed4fec86.png)

此时即可添加host，默认均生效

激活profile时点击Active（如有变动请进行激活否则不会保存）

### 本应用主要依赖于如下项目
- [Tornadofx](https://github.com/edvin/tornadofx)

### 尚未实现的功能

- [ ] host搜索
- [ ] 默认host配置，即不管什么profile均有的配置，不应手动填入(如127.0.0.1 localhost)
- [ ] 未选中profile时，右侧表单禁用
- [ ] 鼠标停在host行上时，tip提示该host的注释信息(如果有)
- [ ] 出于安全考虑为放开导出功能（即将修改保留到原host文件），在程序稳定时放开
- [ ] profile删除，host批量删除
- [ ] 对于同一domain的不同ip，可以考虑下拉列表框单选，而非列出通过勾选控制
### 可能存在的bug

- [ ] 在测试期间，偶尔出现host文件未能还原的情况，由于未能稳定重现，暂时压下，使用时，先自行保留一个备份文件，避免程序错误丢失