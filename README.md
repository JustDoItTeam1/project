## 本项目为使用RuoYi-Vue框架
  仓库内容为施工管理系统的前端代码,主要功能是实现施工方在线提交围蔽申请、交管方在线处理围蔽申请，并通过gis以实现施工项目、围蔽信息在地图上展示和操作。
  
## 开发

```bash


# 1、克隆项目
git clone http://10.28.1.70:10080/enclosed_design_sys/Web_Code.git

# 2、安装Node.js
官网https://nodejs.org/en/下载，安装过程一直next即可
使用管理员身份打开CMD，输入 node -v 和 npm -v，显示版本号即安装成功。
安装node.js后，就已经自带npm，不需要另外再进行安装npm了。

# 3、安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下命令解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

(由于使用的rouyi框架可由上面两个命令自动安装所有依赖）

# 4、启动服务
npm run dev


```

浏览器访问 http://localhost:80

由于目前项目还未全部完成，所以成功启动前端代码需要启动后端，即	enclosed_design_sys /server_code

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```