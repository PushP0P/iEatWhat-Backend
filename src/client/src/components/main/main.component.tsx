import * as React from 'react';
import { MAIN_ROUTES_SWITCH } from '../../router/main.routes';
import { MainComponentProps, MainComponentState } from '../../models/main.model';
import { openIDBUtilities } from 'indexed-db-utilities/dist/utilities/index-db.utility';
import { IDBUtility } from 'indexed-db-utilities/dist/models/idb-utility.model';

export class MainComponent extends React.Component<MainComponentProps, MainComponentState> {
	private stores: IDBUtility;
	constructor(public props: MainComponentProps) {
		super(props);
	}

	public async componentDidMount() {
		// Setting up fake data for development until user services are done
		this.stores = await openIDBUtilities({
			version: 1,
			dbName: 'dev-user-data',
			storeNames: ['userData', 'tokens', 'state'],
			keyPath: 'uDKey'
		});
		this.stores.put('userData', {
			uDKey: 'account',
			id: 'FooBatMetal0',
			name: 'Punky Bruster'
		}).catch(res => {
			console.log('IDBU Add Error', res);
		});

		// END Fake

	}
	public render() {
		return MAIN_ROUTES_SWITCH();
	}
}
