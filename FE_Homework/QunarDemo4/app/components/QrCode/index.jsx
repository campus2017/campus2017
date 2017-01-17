/**
 * @file component QrCode
 */
import './index.scss';

import React from 'react';

function QrCode() {
	return (
		<div className="m-qrcode">
			<h1>手机淘宝</h1>
			<p>睡前卸下一天的疲惫，有你在手心陪伴，心到哪儿便逛到哪儿，惬意生活不过如此。</p>
			<div className="download-info">
				<a className="download-btn">立即下载</a>
				<span>手机扫描快速下载</span>
			</div>
			<img src={ require('../../lib/images/qr_code.png') }/>
		</div>
	);
}

export default QrCode;