
package com.wa9nnn.util.tableui.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
/*1.2*/import com.wa9nnn.util.tableui.html._
/*2.2*/import play.twirl.api.TwirlFeatureImports._

object renderTable extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[com.wa9nnn.util.tableui.Table,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(uiTable: com.wa9nnn.util.tableui.Table):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""<table
    """),_display_(/*6.6*/attribute("id", uiTable.id)),format.raw/*6.33*/("""
    """),_display_(/*7.6*/attributes("class", uiTable.cssClass)),format.raw/*7.43*/("""
        """),format.raw/*8.9*/(""">
    <thead>
    """),_display_(/*10.6*/for(chRow <- uiTable.columnHeaders) yield /*10.41*/ {_display_(Seq[Any](format.raw/*10.43*/("""
        """),format.raw/*11.9*/("""<tr>
        """),_display_(/*12.10*/for(cell <- chRow) yield /*12.28*/ {_display_(Seq[Any](format.raw/*12.30*/("""
            """),format.raw/*13.13*/("""<th
                """),_display_(/*14.18*/span( cell.colSpan, cell.rowSpan)),format.raw/*14.51*/("""
                """),_display_(/*15.18*/attribute("title", cell.tooltip)),format.raw/*15.50*/("""
                """),_display_(/*16.18*/attribute("style", cell.style)),format.raw/*16.48*/("""
                """),_display_(/*17.18*/attributes("class", cell.cssClass)),format.raw/*17.52*/("""
            """),format.raw/*18.13*/(""">"""),_display_(/*18.15*/(cell.value)),format.raw/*18.27*/("""
            """),format.raw/*19.13*/("""</th>
        """)))}),format.raw/*20.10*/("""
        """),format.raw/*21.9*/("""</tr>
    """)))}),format.raw/*22.6*/("""
    """),format.raw/*23.5*/("""</thead>
    <tbody>
    """),_display_(/*25.6*/for(row <- uiTable.rows) yield /*25.30*/ {_display_(Seq[Any](format.raw/*25.32*/("""
        """),format.raw/*26.9*/("""<tr
            """),_display_(/*27.14*/attributes("class", row.cssClass)),format.raw/*27.47*/("""
            """),_display_(/*28.14*/attribute("id", row.rowId)),format.raw/*28.40*/("""
            """),_display_(/*29.14*/attribute("title", row.rowToolTip)),format.raw/*29.48*/("""
        """),format.raw/*30.9*/(""">
        """),_display_(/*31.10*/for(cell <- row.cells) yield /*31.32*/ {_display_(Seq[Any](format.raw/*31.34*/("""
            """),format.raw/*32.13*/("""<td
                """),_display_(/*33.18*/attributes("class", cell.cssClass)),format.raw/*33.52*/("""
                """),_display_(/*34.18*/attribute("id", cell.id)),format.raw/*34.42*/("""
                """),_display_(/*35.18*/attribute("title", cell.tooltip)),format.raw/*35.50*/("""
                """),_display_(/*36.18*/span( cell.colSpan, cell.rowSpan)),format.raw/*36.51*/("""

            """),format.raw/*38.13*/(""">
            """),_display_(/*39.14*/if(cell.href.isDefined)/*39.37*/ {_display_(Seq[Any](format.raw/*39.39*/("""
                """),_display_(/*40.18*/defining(cell.href.get)/*40.41*/ { link =>_display_(Seq[Any](format.raw/*40.51*/("""
                    """),_display_(/*41.22*/if(cell.image.nonEmpty)/*41.45*/ {_display_(Seq[Any](format.raw/*41.47*/("""
                        """),format.raw/*42.25*/("""<a href=""""),_display_(/*42.35*/(link.url)),format.raw/*42.45*/("""" """),_display_(/*42.48*/if(link.target.nonEmpty)/*42.72*/ {_display_(Seq[Any](format.raw/*42.74*/("""
                            """),format.raw/*43.29*/("""target=""""),_display_(/*43.38*/link/*43.42*/.target),format.raw/*43.49*/(""""""")))}),format.raw/*43.51*/("""><img src=""""),_display_(/*43.63*/(cell.image.head)),format.raw/*43.80*/(""""></a>
                    """)))}/*44.23*/else/*44.28*/{_display_(Seq[Any](format.raw/*44.29*/("""
                        """),format.raw/*45.25*/("""<a href=""""),_display_(/*45.35*/(link.url)),format.raw/*45.45*/("""" """),_display_(/*45.48*/if(link.target.nonEmpty)/*45.72*/ {_display_(Seq[Any](format.raw/*45.74*/("""
                            """),format.raw/*46.29*/("""target=""""),_display_(/*46.38*/link/*46.42*/.target),format.raw/*46.49*/(""""""")))}),format.raw/*46.51*/(""">"""),_display_(/*46.53*/(cell.value)),format.raw/*46.65*/("""</a>
                    """)))}),format.raw/*47.22*/("""
                """)))}),format.raw/*48.18*/("""
            """)))}/*49.15*/else/*49.20*/{_display_(Seq[Any](format.raw/*49.21*/("""
                """),_display_(/*50.18*/if(cell.button)/*50.33*/ {_display_(Seq[Any](format.raw/*50.35*/("""
                    """),format.raw/*51.21*/("""<button class=""""),_display_(/*51.37*/(cell.button.get)),format.raw/*51.54*/("""">"""),_display_(/*51.57*/(cell.value)),format.raw/*51.69*/("""</button>
                """)))}/*52.19*/else/*52.24*/{_display_(Seq[Any](format.raw/*52.25*/("""
                    """),_display_(/*53.22*/if(cell.rawHtml)/*53.38*/ {_display_(Seq[Any](format.raw/*53.40*/("""
                        """),_display_(/*54.26*/Html(cell.value)),format.raw/*54.42*/("""
                    """)))}/*55.23*/else/*55.28*/{_display_(Seq[Any](format.raw/*55.29*/("""
                        """),_display_(/*56.26*/if(cell.image.nonEmpty)/*56.49*/ {_display_(Seq[Any](format.raw/*56.51*/("""
                            """),_display_(/*57.30*/for(image ← cell.image) yield /*57.53*/ {_display_(Seq[Any](format.raw/*57.55*/("""
                                """),format.raw/*58.33*/("""<img src=""""),_display_(/*58.44*/(image)),format.raw/*58.51*/("""">
                                """)))}),format.raw/*59.34*/("""
                        """)))}/*60.27*/else/*60.32*/{_display_(Seq[Any](format.raw/*60.33*/("""
                            """),_display_(/*61.30*/(cell.value)),format.raw/*61.42*/("""
                        """)))}),format.raw/*62.26*/("""
                    """)))}),format.raw/*63.22*/("""
                """)))}),format.raw/*64.18*/("""
            """)))}),format.raw/*65.14*/(""" """),format.raw/*65.15*/("""</td>
        """)))}),format.raw/*66.10*/("""
        """),format.raw/*67.9*/("""</tr>
    """)))}),format.raw/*68.6*/("""
"""),format.raw/*69.1*/("""</tbody>
</table>

"""))
      }
    }
  }

  def render(uiTable:com.wa9nnn.util.tableui.Table): play.twirl.api.HtmlFormat.Appendable = apply(uiTable)

  def f:((com.wa9nnn.util.tableui.Table) => play.twirl.api.HtmlFormat.Appendable) = (uiTable) => apply(uiTable)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: src/main/twirl/com.wa9nnn.util.tableui/renderTable.scala.html
                  HASH: e7c15118922f5d35484f5d762074b1bfa3b80acf
                  MATRIX: 287->1|332->40|709->86|843->127|880->139|927->166|958->172|1015->209|1050->218|1095->237|1146->272|1186->274|1222->283|1263->297|1297->315|1337->317|1378->330|1426->351|1480->384|1525->402|1578->434|1623->452|1674->482|1719->500|1774->534|1815->547|1844->549|1877->561|1918->574|1964->589|2000->598|2041->609|2073->614|2125->640|2165->664|2205->666|2241->675|2285->692|2339->725|2380->739|2427->765|2468->779|2523->813|2559->822|2597->833|2635->855|2675->857|2716->870|2764->891|2819->925|2864->943|2909->967|2954->985|3007->1017|3052->1035|3106->1068|3148->1082|3190->1097|3222->1120|3262->1122|3307->1140|3339->1163|3387->1173|3436->1195|3468->1218|3508->1220|3561->1245|3598->1255|3629->1265|3659->1268|3692->1292|3732->1294|3789->1323|3825->1332|3838->1336|3866->1343|3899->1345|3938->1357|3976->1374|4023->1403|4036->1408|4075->1409|4128->1434|4165->1444|4196->1454|4226->1457|4259->1481|4299->1483|4356->1512|4392->1521|4405->1525|4433->1532|4466->1534|4495->1536|4528->1548|4585->1574|4634->1592|4667->1607|4680->1612|4719->1613|4764->1631|4788->1646|4828->1648|4877->1669|4920->1685|4958->1702|4988->1705|5021->1717|5067->1745|5080->1750|5119->1751|5168->1773|5193->1789|5233->1791|5286->1817|5323->1833|5364->1856|5377->1861|5416->1862|5469->1888|5501->1911|5541->1913|5598->1943|5637->1966|5677->1968|5738->2001|5776->2012|5804->2019|5871->2055|5916->2082|5929->2087|5968->2088|6025->2118|6058->2130|6115->2156|6168->2178|6217->2196|6262->2210|6291->2211|6337->2226|6373->2235|6414->2246|6442->2247
                  LINES: 10->1|11->2|16->4|21->5|22->6|22->6|23->7|23->7|24->8|26->10|26->10|26->10|27->11|28->12|28->12|28->12|29->13|30->14|30->14|31->15|31->15|32->16|32->16|33->17|33->17|34->18|34->18|34->18|35->19|36->20|37->21|38->22|39->23|41->25|41->25|41->25|42->26|43->27|43->27|44->28|44->28|45->29|45->29|46->30|47->31|47->31|47->31|48->32|49->33|49->33|50->34|50->34|51->35|51->35|52->36|52->36|54->38|55->39|55->39|55->39|56->40|56->40|56->40|57->41|57->41|57->41|58->42|58->42|58->42|58->42|58->42|58->42|59->43|59->43|59->43|59->43|59->43|59->43|59->43|60->44|60->44|60->44|61->45|61->45|61->45|61->45|61->45|61->45|62->46|62->46|62->46|62->46|62->46|62->46|62->46|63->47|64->48|65->49|65->49|65->49|66->50|66->50|66->50|67->51|67->51|67->51|67->51|67->51|68->52|68->52|68->52|69->53|69->53|69->53|70->54|70->54|71->55|71->55|71->55|72->56|72->56|72->56|73->57|73->57|73->57|74->58|74->58|74->58|75->59|76->60|76->60|76->60|77->61|77->61|78->62|79->63|80->64|81->65|81->65|82->66|83->67|84->68|85->69
                  -- GENERATED --
              */
          