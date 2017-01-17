/**
 * @file component Header
 */
import './index.scss';
import React from 'react';

function Header() {
	return (
		<div className="m-header">
			<div className="m-header-login">
				<a className="login" href="#">亲，请登录</a>
				<a href="#">免费注册</a>
				<a href="#">手机逛淘宝</a>
			</div>
			<nav className="m-header-nav">
				<a className="index" href="#">淘宝网首页</a>
				<a href="#">我的淘宝</a>
				<a href="#">购物车<span className="count">0</span></a>
				<a href="#">收藏夹</a>
				<a href="#">商品分类</a>
				<a href="#">卖家中心</a>
				<a href="#">联系客服</a>
				<a href="#">网站导航</a>
			</nav>
		</div>
	);
}

export default Header;