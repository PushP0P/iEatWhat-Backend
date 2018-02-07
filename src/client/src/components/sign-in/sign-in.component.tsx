import * as React from 'react';
import {
	SIGN_IN_OPTIONS_INITIALE_STATE,
	SignInComponentProps, SignInComponentState,
} from '../../models/sign-in.model';
import { SignInOptions } from './sign-in-options.controlled';
import { getSignInOptions } from '../../services/sign-in.service';
import { Link } from 'react-router-dom';

export class SignInComponent extends React.Component<SignInComponentProps, SignInComponentState> {

	constructor(public props: SignInComponentProps) {
		super(props);
		this.state = SIGN_IN_OPTIONS_INITIALE_STATE;
	}

	public async componentDidMount(): Promise<void> {
		const signInOptions = await getSignInOptions();
		this.setState({
			signInOptions: signInOptions
		});
	}

	public render() {
		return(
			<div
				className="sign-in-component container text-center"
			>
				<p>Please, sign up or In with the following options.</p>
				<SignInOptions
					signInOptions={this.state.signInOptions || []}
				/>
				<div className="sign_in_footer">
					<div>
						<Link to="/privacy" >
							Privacy
						</Link>
					</div>
					<div>
						<Link to="/legal" >
							Legal Stuff
						</Link>
					</div>
					<div>
						<Link to="/about" >
							About
						</Link>
					</div>
				</div>
			</div>
		);
	}
}
