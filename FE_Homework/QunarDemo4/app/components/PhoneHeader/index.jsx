/**
 * @file component PhoneHeader
 */
import './index.scss';
import React, { PropTypes } from 'react';

import Logo from '../Logo';
import PhoneNav from '../PhoneNav';

const propTypes = {
	onGetDetail: PropTypes.func.isRequired
};

function PhoneHeader({ onGetDetail }) {
	return (
		<div className="m-phone-header">
			<Logo />
			<PhoneNav onGetDetail={ onGetDetail } />
		</div>
	);
}

PhoneHeader.propTypes = propTypes;
export default PhoneHeader;