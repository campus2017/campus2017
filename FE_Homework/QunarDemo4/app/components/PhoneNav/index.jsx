/**
 * @file component PhoneNav
 */
import './index.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	onGetDetail: PropTypes.func.isRequired
};

function PhoneNav({ onGetDetail }) {
	return (
		<ul className="m-phone-nav">
			<li className="list" onClick={ onGetDetail } >
				<a href="#">Android</a>
			</li>
			<li className="list" onClick={ onGetDetail } >
				<a href="#">iphone</a>
			</li>
			<li className="list" onClick={ onGetDetail } >
				<a href="#">ipad</a>
			</li>
			<li className="list" onClick={ onGetDetail } >
				<a href="#">AndroidPad</a>
			</li>
			<li className="list" onClick={ onGetDetail } >
				<a href="#">WinPhone</a>
			</li>
			<li className="list" onClick={ onGetDetail } >
				<a href="#">软件市场</a>
			</li>
		</ul>
	);
}

PhoneNav.propTypes = propTypes;

export default PhoneNav;