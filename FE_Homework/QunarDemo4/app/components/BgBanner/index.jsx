/**
 * @file component BgBanner
 */
import './index.scss';
import React from 'react';

import QrCode from '../QrCode';

function BgBanner() {
	return (
		<div className="m-bg-banner">
			<img src={ require('../../lib/images/bg_banner.jpg') } />
			<QrCode />
		</div>
	);
}

export default BgBanner;