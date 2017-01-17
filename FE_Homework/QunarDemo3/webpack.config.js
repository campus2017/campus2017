var path = require('path'),
	webpack = require('webpack'),
	HtmlwebpackPlugin = require('html-webpack-plugin');
//一些常用路径
var ROOT_PATH = path.resolve(__dirname),
	APP_PATH = path.resolve(ROOT_PATH, 'app'),
	BUILD_PATH = path.resolve(ROOT_PATH, 'build');

module.exports = {
	entry: {
		app: path.resolve(APP_PATH, 'app.jsx')
	},
	output: {
		path: BUILD_PATH,
		filename: 'bundle.js'
	},
	//开启dev source map
	devtool: 'eval-source-map',
	//开启webpack dev server
	devServer: {
		historyApiFallback: true,
		hot: true,
		inline: true,
		progress: true
	},

	module: {
		//配置 preLoaders，将eslint添加进去
		preLoaders: [{
			test: /\.jsx?$/,
			loaders: ['eslint?parser=babel-eslint'],
			include: APP_PATH
		}],
		//配置loader，将babel添加进去
		loaders: [{
			test: /\.jsx?$/,
			loaders: ['babel'],
			include: APP_PATH
		}, {
			test: /\.scss$/,
			loaders: ['style', 'css', 'sass']
		}]
	},
	//配置plugin
	plugins: [
		new HtmlwebpackPlugin({
			title: 'demo3'
		})
	],
	resolve: {
		extensions: ['', '.js', '.jsx']
	}
}