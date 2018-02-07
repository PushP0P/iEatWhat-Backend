/**
 * FIXTURES : dummy data
 */

export const FIXTURE_SIGN_IN_OPTIONS: SignInOption[] = [
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

/**
 *  END FIXTURES
 */

export interface SignInOption {
	id: string;
	title: string;
	link: string;
	icon: string;
}

export interface SignInOptionsProps {
	signInOptions: SignInOption[];
}

export interface SignInComponentProps {

}

export interface SignInComponentState {
	signInOptions: SignInOption[];
}

export const SIGN_IN_OPTIONS_INITIALE_STATE: SignInComponentState = {
	signInOptions: []
};
