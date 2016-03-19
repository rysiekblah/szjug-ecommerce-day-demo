class ProductDetailsCtrl

    constructor: (@$log, @$location, @$routeParams, @ProductService) ->
        @$log.debug "ProductDetailsCtrl created-"
        @product = {}
        @clients= {}
        this.details()

    details: (slug) ->
      @ProductService.details(@$routeParams.slug).then(
        (data) => @product = data.data
      )
      @ProductService.clients(@$routeParams.slug).then(
        (data) =>
          @$log.debug "Clients: " + JSON.stringify(data.data)
          @clients = data.data
      )

controllersModule.controller('ProductDetailsCtrl', ['$log', '$location', '$routeParams', 'ProductService', ProductDetailsCtrl])
