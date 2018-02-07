/********************************************************************************
 ,-.----.                                       ,-.----.                 ,-.----.
 \    /  \                             ,---,    \    /  \      ,----..   \    /  \
 |   :    \                          ,--.' |    |   :    \    /   /   \  |   :    \
 |   |  .\ :         ,--,            |  |  :    |   |  .\ :  /   .     : |   |  .\ :
 .   :  |: |       ,'_ /|   .--.--.  :  :  :    .   :  |: | .   /   ;.  \.   :  |: |
 |   |   \ :  .--. |  | :  /  /    ' :  |  |,--.|   |   \ :.   ;   /  ` ;|   |   \ :
 |   : .   /,'_ /| :  . | |  :  /`./ |  :  '   ||   : .   /;   |  ; \ ; ||   : .   /
 ;   | |`-' |  ' | |  . . |  :  ;_   |  |   /' :;   | |`-' |   :  | ; | ';   | |`-'
 |   | ;    |  | ' |  | |  \  \    `.'  :  | | ||   | ;    .   |  ' ' ' :|   | ;
 :   ' |    :  | : ;  ; |   `----.   \  |  ' | ::   ' |    '   ;  \; /  |:   ' |
 :   : :    '  :  `--'   \ /  /`--'  /  :  :_:,':   : :     \   \  ',  / :   : :
 |   | :    :  ,      .-./'--'.     /|  | ,'    |   | :      ;   :    /  |   | :
 `---'.|     `--`----'      `--'---' `--''      `---'.|       \   \ .'   `---'.|
 `---`                                          `---`        `---`       `---`
 *******************************************************************************/
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { Router } from 'react-router';
import { MainComponent } from './components/main/main.component';
import history from './router/router.history';
import './main.style.css';
import { registerWorkers } from './registerServiceWorker';
if ('serviceWorker' in navigator) {
	console.log('booting SW');
	registerWorkers();
}

ReactDOM.render(
	<Router history={history}>
		<MainComponent/>
	</Router>,
	document.getElementById('iEatWhat') as HTMLElement
);
