var path = require('path'),
	webpack = require('webpack'),
	HtmlwebpackPlugin = require('html-webpack-plugin');
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
	devtool: 'eval-source-map',
	devServer: {
		historyApiFallback: true,
		hot: true,
		inline: true,
		progress: true
	},
	module: {
		preLoaders: [{
			test: /\.jsx?$/,
			loaders: ['eslint?parser=babel-eslint'],
			include: APP_PATH
		}],
		loaders: [{
			test: /\.jsx?$/,
			loaders: ['babel'],
			include: APP_PATH
		}, {
			test: /\.scss$/,
			loaders: ['style', 'css', 'sass']
		}, {
			test: /\.(png|jpe?g|gif)$/,
			loader: 'url?limit=8192&name=img/[hash:8].[ext]'
		}]
	},
	plugins: [
		new HtmlwebpackPlugin({
			title: 'airport'
		})
	],
	resolve: {
		extensions: ['', '.js', '.jsx']
	}
}