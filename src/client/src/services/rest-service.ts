/**
 * Basic REST request that returns a promise.
 *
 * @param {string} path
 * @param {string} verb
 * @param {{}} headers
 * @param {{}} body
 * @returns {Promise<{}>}
 */

export async function httpRequest(path: string, verb?: string, headers?: {}, body?: {} ): Promise<Response> {
	const req: Request = await new Request(path, <RequestInit> {
		method: verb || 'get',
		cache: 'default',
		mode: 'cors',
		headers: headers ? await new Headers(headers) : {},
		body: body ? body : null
	});
	const response: Response | void = await fetch(req)
		.catch(err => alert('Fetch Error: ' + err)) as Response;
	return await response.json();
}
