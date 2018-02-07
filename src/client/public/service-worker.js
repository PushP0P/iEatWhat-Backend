console.log('SW In Da Hizzy!!!', self);
self.addEventListener('fetch', (evt) => {
   console.log('worker hijacked fetch,', evt);
});
