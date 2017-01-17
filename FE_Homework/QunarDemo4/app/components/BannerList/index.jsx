/**
 * @file component BannerList
 */
import './index.scss';
import React from 'react';

function BannerList() {
	return (
		<ul className="m-banner-list">
			<li className="list">
				<img src={ require('../../lib/images/m_banner1.png') } />
				<p>微淘，上新，折扣，团购一网打尽</p>
			</li>
			<li className="list">
				<img src={ require('../../lib/images/m_banner2.png') } />
				<p>淘火眼，最强大的扫码软件</p>
			</li>
			<li className="list">
				<img src={ require('../../lib/images/m_banner3.png') } />
				<p>淘宝旅行，一站式旅行服务</p>
			</li>
			<li className="list">
				<img src={ require('../../lib/images/m_banner4.png') } />
				<p>淘宝手机助手，能赚钱的应用商店</p>
			</li>
		</ul>
	);
}

export default BannerList;