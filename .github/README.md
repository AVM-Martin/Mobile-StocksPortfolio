# Mobile-StocksPortfolio

Stocks Portfolio is an application that will store Indonesian stock transactions history in a local database. It also provides a simple security layer so intruder guests can not snoop on your transactions. Since it has been developed in a mobile version, Stock Portfolio will be a handy application.

<img src="https://github.com/AVM-Martin/Mobile-StocksPortfolio/blob/master/.github/screenshot.png" height="500px">


## Contributors

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/ekeitaro/">
        <img src="https://github.com/ekeitaro.png" width="100px;" alt=""/><br />
        <sub><b>Edward Keitaro</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/mikechrist21/">
        <img src="https://github.com/mikechrist21.png" width="100px;" alt=""/><br />
        <sub><b>Mike Christ</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://AVM-Martin.my.id/">
        <img src="https://github.com/AVM-Martin.png" width="100px;" alt=""/><br />
        <sub><b>Andreas Martin</b></sub>
      </a>
    </td>
  </tr>
</table>


## Creative Thinking

  * Use `StocksPortfolio` as the main class and the main data controller
  * Use MVP architecture as its clean architecture to support clean code, with the view layer and the presenter layer merged (to simplify the project complexity)
  * Use `utils/*` as helper classes
  * Use online data of the last price


## References

Here a list of URLs that had been accessed and (maybe) give some insights to this project

  * [Good SQLite Tutorial](https://developer.android.com/training/data-storage/sqlite#PersistingDbConnection) per May 31, 2020
  * [Good Singleton Pattern on Volley](https://developer.android.com/training/volley/requestqueue#singleton) per June 02, 2020
  * [Relative Layout Design](https://stackoverflow.com/questions/8319112/relativelayout-scrollview-and-navigation-bar-at-bottom)


### Resources

  * [Broker List](https://www.idx.co.id/data-pasar/ringkasan-perdagangan/ringkasan-broker/) per May 27, 2020
  * [Stock List](https://www.idx.co.id/data-pasar/ringkasan-perdagangan/ringkasan-saham/) per May 29, 2020


### Image Resources

  * https://image.flaticon.com/icons/png/512/8/8820.png
  * https://www.freepik.com/free-icon/home_744469.htm#page=1&query=home&position=0
  * https://www.freepik.com/free-icon/logout_970906.htm#page=1&query=exit&position=8
  * https://www.freepik.com/free-icon/line-chart_934432.htm#page=1&query=account&position=2
  * https://www.freepik.com/free-icon/user-silhouette_776634.htm#page=1&query=account&position=18
  * https://www.freepik.com/free-icon/delete-button_778873.htm#page=1&query=delete&position=0
  * https://www.freepik.com/free-icon/write_863159.htm#page=1&query=edit&position=2
  * https://www.freepik.com/free-icon/contract_905938.htm#page=1&query=writing&position=1


## Device Specifications

Here a list of devices used in testing phase

  * API 28 Android 9: Asus ZenFone Max Pro M1
  * API 24 Android 7.0: Nexus 5X (*Emulator*)
  * API 24 Android 7.0: Pixel 2 (*Emulator*)
  * API 23 Android 6.0: Nexus S (*Emulator*)
  * API 27 Android 8.1.0: Pixel 2 (*Emulator*)
  * API 23 Android 6.0: phone
