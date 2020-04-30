#### Introduction:

We provide different processing tools in different modules to help developers better develop their applications.

#### modules:

asynclayoutinflater: Provide asynchronous loading layout related tools.

#### How to use:

- about asynclayoutinflater

  ```kotlin
  //in your application or any you can get a context
  //inflate like this
  AsyncInflaterHelper.inflate(InflateConfig(this, "tag", R.layout.layout_center_image))
  //fetch a view in your UI thread
  AsyncInflaterHelper.fetchView(
  		InflateConfig(
  				this,
  				"main",
  				R.layout.layout_center_image
  		)
  )
  ```

  