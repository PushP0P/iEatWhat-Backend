import * as React from 'react';
import { SignInOption, SignInOptionsProps } from '../../models/sign-in.model';
import { SVGS } from '../../assets/react-svgs.asset';

export const SignInOptions = (props: SignInOptionsProps) => {
	return (
		<div
			className="sign-in-options"
		>
			{
				props.signInOptions
					.map((signInOption: SignInOption) => {
						return (
							<div
								className="sign_in_option btn-lg btn-outline-primary"
								key={signInOption.id.toString()}
							>
								<div
									className="sign_in_title"
								>
									{signInOption.title}
								</div>
								{SVGS[signInOption.icon]}
							</div>
						);
					})
			}
		</div>
	);
};
