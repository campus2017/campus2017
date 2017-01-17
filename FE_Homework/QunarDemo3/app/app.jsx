/**
 * @file app main file
 */
import React from 'react';
import ReactDOM from 'react-dom';
import Exchangebill from './components/Exchangebill';

const app = document.createElement('div');
document.body.appendChild(app);
ReactDOM.render(<Exchangebill />, app);