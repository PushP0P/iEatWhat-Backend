//tslint:disable
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Router} from 'react-router';
import * as renderer from 'react-test-renderer';
import history from '../../router/router.history';
import {SignInOptions} from './sign-in-options.controlled';

let component: any;
let doc: any = document;
const FIXTURE_SIGN_IN_OPTIONS = [
	{
		id: '1234',
		title: 'Google Sign-In',
		link: '#',
		icon: 'google',
	},
	{
		id: '5678',
		title: 'Twitter Sign-In',
		link: '#',
		icon: 'twitter',
	}
];

it('renders without crashing', () => {
	doc.createElement('div');
	component = ReactDOM.render(
		<Router
			history={history}
		>
		</Router>,
		doc);
});

it(`SignInOptions has ${FIXTURE_SIGN_IN_OPTIONS.length} buttons`, () => {
	component = renderer.create(
		<SignInOptions signInOptions={FIXTURE_SIGN_IN_OPTIONS}/>
	);
	let tree = component.toJSON();
	expect(tree).toMatchSnapshot();
	expect(FIXTURE_SIGN_IN_OPTIONS.length).toBe(tree.children.length);
});
