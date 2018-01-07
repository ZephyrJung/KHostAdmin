# KHostAdmin

基于Java 8 Host管理工具

## plan

左边是list，右边是可搜索表格列表
右侧下方有三个按钮Save，Save As，和Export

- Save 保存当前Profile
- Save As可以新建一个Profile
- Export 写入到Host文件中

如果不写入，在应用打开时，当前host会被替换成当前profile，关闭前将被替换回来

profile通过json格式文件保存

json文件包含host的完整信息，profile生效时会替换host中所有相同domain的IP设置

