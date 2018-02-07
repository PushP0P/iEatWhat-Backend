import { FIXTURE_SIGN_IN_OPTIONS } from '../models/sign-in.model';

export async function getSignInOptions() {
	return Promise.resolve(FIXTURE_SIGN_IN_OPTIONS);
}
