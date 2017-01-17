/**
 * @file component PhoneDetails
 */
import './index.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	onCloseDetail: PropTypes.func.isRequired,
	shouldShow: PropTypes.bool.isRequired
};

function PhoneDetails({ onCloseDetail, shouldShow }) {
	return (
		<div className={ shouldShow ? "m-phone-details" : "hide"}>
			<ul>
				<li className="list">
					<img src={ require('../../lib/images/detail_android.png') } />
				</li>
				<li className="list">
					<img src={ require('../../lib/images/detail_iphone.png') } />
				</li>
				<li className="list">
					<img src={ require('../../lib/images/detail_ipad.png') } />
				</li>
				<li className="list">
					<img src={ require('../../lib/images/detail_androidPad.png') } />
				</li>
				<li className="list">
					<img src={ require('../../lib/images/detail_winPhone.png') } />
				</li>
			</ul>
			<div className="download">
				<p>直接下载</p>
				<div className="download-btns">
					<a href="#" className="free_download"></a>
					<a href="#" className="download-pc"></a>
				</div>
			</div>
			<div className="qr-code">
				<img src={ require('../../lib/images/tao_qr_code.png') } />
				<p>手机扫描二维码下载</p>
			</div>
			<span className="close" onClick={ onCloseDetail } ></span>
		</div>
	);
}

export default PhoneDetails;