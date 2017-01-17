/**
 * @file component Logo
 */
import './index.scss';
import React from 'react';

function Logo() {
	return (
		<div className="m-logo">
			<img src={ require('../../lib/images/taobao_logo.png') } />
		</div>
	);
}

export default Logo;