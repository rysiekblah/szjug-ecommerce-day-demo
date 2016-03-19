class ProductService

  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = { headers: @headers }

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "Constructing ProductService"

  details: (name) ->
    deffer = @$q.defer()
    @$http.get('/details/' + name)
      .success((data,status,headers) => deffer.resolve(data))
      .error((data,status,headers) => deffer.reject(data))

  clients: (slug) ->
    deffer = @$q.defer()
    @$http.get('/clients/' + slug)
      .success((data,status,header) => deffer.resolve(data))
      .error((data,status,header) => deffer.reject(data))

  products: () ->
    deffer = @$q.defer()
    @$http.get('/products')
      .success((data,status,headers) => deffer.resolve(data))
      .error((data,status,headers) => deffer.reject(data))

servicesModule.service('ProductService', ['$log', '$http', '$q', ProductService])
