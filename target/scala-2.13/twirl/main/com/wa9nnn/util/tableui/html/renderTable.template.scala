
package com.wa9nnn.util.tableui.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
/*1.2*/import play.twirl.api.TwirlFeatureImports._

object renderTable extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[com.wa9nnn.util.tableui.Table,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(uiTable: com.wa9nnn.util.tableui.Table):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""<table class = """"),_display_(/*3.18*/uiTable/*3.25*/.cssClass),format.raw/*3.34*/(""""

    """),_display_(/*5.6*/if(uiTable.id)/*5.20*/ {_display_(Seq[Any](format.raw/*5.22*/("""
    """),format.raw/*6.5*/("""id=""""),_display_(/*6.10*/uiTable/*6.17*/.id.get),format.raw/*6.24*/(""""
""")))}),format.raw/*7.2*/("""
"""),format.raw/*8.1*/(""">
<thead>
"""),_display_(/*10.2*/for(chRow <- uiTable.columnHeaders) yield /*10.37*/ {_display_(Seq[Any](format.raw/*10.39*/("""
    """),format.raw/*11.5*/("""<tr>
    """),_display_(/*12.6*/for(cell <- chRow) yield /*12.24*/ {_display_(Seq[Any](format.raw/*12.26*/("""
        """),format.raw/*13.9*/("""<th rowspan=""""),_display_(/*13.23*/(cell.rowSpan)),format.raw/*13.37*/("""" colspan=""""),_display_(/*13.49*/(cell.colSpan)),format.raw/*13.63*/("""" """),_display_(/*13.66*/if(cell.style)/*13.80*/ {_display_(Seq[Any](format.raw/*13.82*/("""
            """),format.raw/*14.13*/("""style=""""),_display_(/*14.21*/(cell.style.get)),format.raw/*14.37*/(""""
        """)))}),format.raw/*15.10*/("""
        """),format.raw/*16.9*/("""class=""""),_display_(/*16.17*/cell/*16.21*/.renderedCssClass),format.raw/*16.38*/(""""
        title=""""),_display_(/*17.17*/(cell.tooltip)),format.raw/*17.31*/(""""
    >"""),_display_(/*18.7*/(cell.value)),format.raw/*18.19*/("""
        """),format.raw/*19.9*/("""</th>
    """)))}),format.raw/*20.6*/("""
    """),format.raw/*21.5*/("""</tr>
""")))}),format.raw/*22.2*/("""
"""),format.raw/*23.1*/("""</thead>
<tbody>
"""),_display_(/*25.2*/for(row <- uiTable.rows) yield /*25.26*/ {_display_(Seq[Any](format.raw/*25.28*/("""
    """),format.raw/*26.5*/("""<tr class=""""),_display_(/*26.17*/(row.cssClass)),format.raw/*26.31*/("""" """),_display_(/*26.34*/(row.rowId match {
        case None =>
            ""
        case Some(id) =>
            s"id='$id'"
    })),format.raw/*31.7*/("""
    """),format.raw/*32.5*/("""title = """"),_display_(/*32.15*/(row.rowToolTip)),format.raw/*32.31*/(""""
    >
    """),_display_(/*34.6*/for(cell <- row.cells) yield /*34.28*/ {_display_(Seq[Any](format.raw/*34.30*/("""
        """),format.raw/*35.9*/("""<td rowspan=""""),_display_(/*35.23*/(cell.rowSpan)),format.raw/*35.37*/("""" colspan=""""),_display_(/*35.49*/(cell.colSpan)),format.raw/*35.63*/(""""
        class=""""),_display_(/*36.17*/cell/*36.21*/.renderedCssClass),format.raw/*36.38*/(""""
        title=""""),_display_(/*37.17*/(cell.tooltip)),format.raw/*37.31*/(""""
            """),_display_(/*38.14*/if(cell.style)/*38.28*/ {_display_(Seq[Any](format.raw/*38.30*/("""
                """),format.raw/*39.17*/("""style=""""),_display_(/*39.25*/(cell.style.get)),format.raw/*39.41*/(""""
                """)))}),format.raw/*40.18*/("""
            """),_display_(/*41.14*/if(cell.id.isDefined)/*41.35*/ {_display_(Seq[Any](format.raw/*41.37*/("""
                """),format.raw/*42.17*/("""id=""""),_display_(/*42.22*/cell/*42.26*/.id.get),format.raw/*42.33*/(""""
            """)))}),format.raw/*43.14*/("""
        """),format.raw/*44.9*/("""> """),_display_(/*44.12*/if(cell.href.isDefined)/*44.35*/ {_display_(Seq[Any](format.raw/*44.37*/("""
            """),_display_(/*45.14*/defining(cell.href.get)/*45.37*/ { link =>_display_(Seq[Any](format.raw/*45.47*/("""
                """),_display_(/*46.18*/if(cell.image.nonEmpty)/*46.41*/ {_display_(Seq[Any](format.raw/*46.43*/("""
                    """),format.raw/*47.21*/("""<a href=""""),_display_(/*47.31*/(link.url)),format.raw/*47.41*/("""" """),_display_(/*47.44*/if(link.target.nonEmpty)/*47.68*/ {_display_(Seq[Any](format.raw/*47.70*/("""
                        """),format.raw/*48.25*/("""target=""""),_display_(/*48.34*/link/*48.38*/.target),format.raw/*48.45*/(""""""")))}),format.raw/*48.47*/("""><img src=""""),_display_(/*48.59*/(cell.image.head)),format.raw/*48.76*/(""""></a>
                """)))}/*49.19*/else/*49.24*/{_display_(Seq[Any](format.raw/*49.25*/("""
                    """),format.raw/*50.21*/("""<a href=""""),_display_(/*50.31*/(link.url)),format.raw/*50.41*/("""" """),_display_(/*50.44*/if(link.target.nonEmpty)/*50.68*/ {_display_(Seq[Any](format.raw/*50.70*/("""
                        """),format.raw/*51.25*/("""target=""""),_display_(/*51.34*/link/*51.38*/.target),format.raw/*51.45*/(""""""")))}),format.raw/*51.47*/(""">"""),_display_(/*51.49*/(cell.value)),format.raw/*51.61*/("""</a>
                """)))}),format.raw/*52.18*/("""
            """)))}),format.raw/*53.14*/("""
        """)))}/*54.11*/else/*54.16*/{_display_(Seq[Any](format.raw/*54.17*/("""
            """),_display_(/*55.14*/if(cell.button)/*55.29*/ {_display_(Seq[Any](format.raw/*55.31*/("""
                """),format.raw/*56.17*/("""<button class=""""),_display_(/*56.33*/(cell.button.get)),format.raw/*56.50*/("""">"""),_display_(/*56.53*/(cell.value)),format.raw/*56.65*/("""</button>
            """)))}/*57.15*/else/*57.20*/{_display_(Seq[Any](format.raw/*57.21*/("""
                """),_display_(/*58.18*/if(cell.rawHtml)/*58.34*/ {_display_(Seq[Any](format.raw/*58.36*/("""
                    """),_display_(/*59.22*/Html(cell.value)),format.raw/*59.38*/("""
                """)))}/*60.19*/else/*60.24*/{_display_(Seq[Any](format.raw/*60.25*/("""
                    """),_display_(/*61.22*/if(cell.image.nonEmpty)/*61.45*/ {_display_(Seq[Any](format.raw/*61.47*/("""
                        """),_display_(/*62.26*/for(image ← cell.image) yield /*62.49*/ {_display_(Seq[Any](format.raw/*62.51*/("""
                            """),format.raw/*63.29*/("""<img src=""""),_display_(/*63.40*/(image)),format.raw/*63.47*/("""">
                        """)))}),format.raw/*64.26*/("""
                    """)))}/*65.23*/else/*65.28*/{_display_(Seq[Any](format.raw/*65.29*/("""
                        """),_display_(/*66.26*/(cell.value)),format.raw/*66.38*/("""
                    """)))}),format.raw/*67.22*/("""
                """)))}),format.raw/*68.18*/("""
            """)))}),format.raw/*69.14*/("""
        """)))}),format.raw/*70.10*/("""</td>
    """)))}),format.raw/*71.6*/("""
    """),format.raw/*72.5*/("""</tr>
""")))}),format.raw/*73.2*/("""
"""),format.raw/*74.1*/("""</tbody>
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
                  HASH: 6b412da3473e6413b98a43ad77947e4b59ac18d2
                  MATRIX: 287->1|664->46|798->87|841->104|856->111|885->120|918->128|940->142|979->144|1010->149|1041->154|1056->161|1083->168|1115->171|1142->172|1179->183|1230->218|1270->220|1302->225|1338->235|1372->253|1412->255|1448->264|1489->278|1524->292|1563->304|1598->318|1628->321|1651->335|1691->337|1732->350|1767->358|1804->374|1846->385|1882->394|1917->402|1930->406|1968->423|2013->441|2048->455|2082->463|2115->475|2151->484|2192->495|2224->500|2261->507|2289->508|2333->526|2373->550|2413->552|2445->557|2484->569|2519->583|2549->586|2679->696|2711->701|2748->711|2785->727|2824->740|2862->762|2902->764|2938->773|2979->787|3014->801|3053->813|3088->827|3133->845|3146->849|3184->866|3229->884|3264->898|3306->913|3329->927|3369->929|3414->946|3449->954|3486->970|3536->989|3577->1003|3607->1024|3647->1026|3692->1043|3724->1048|3737->1052|3765->1059|3811->1074|3847->1083|3877->1086|3909->1109|3949->1111|3990->1125|4022->1148|4070->1158|4115->1176|4147->1199|4187->1201|4236->1222|4273->1232|4304->1242|4334->1245|4367->1269|4407->1271|4460->1296|4496->1305|4509->1309|4537->1316|4570->1318|4609->1330|4647->1347|4690->1372|4703->1377|4742->1378|4791->1399|4828->1409|4859->1419|4889->1422|4922->1446|4962->1448|5015->1473|5051->1482|5064->1486|5092->1493|5125->1495|5154->1497|5187->1509|5240->1531|5285->1545|5314->1556|5327->1561|5366->1562|5407->1576|5431->1591|5471->1593|5516->1610|5559->1626|5597->1643|5627->1646|5660->1658|5702->1682|5715->1687|5754->1688|5799->1706|5824->1722|5864->1724|5913->1746|5950->1762|5987->1781|6000->1786|6039->1787|6088->1809|6120->1832|6160->1834|6213->1860|6252->1883|6292->1885|6349->1914|6387->1925|6415->1932|6474->1960|6515->1983|6528->1988|6567->1989|6620->2015|6653->2027|6706->2049|6755->2067|6800->2081|6841->2091|6882->2102|6914->2107|6951->2114|6979->2115
                  LINES: 10->1|15->2|20->3|20->3|20->3|20->3|22->5|22->5|22->5|23->6|23->6|23->6|23->6|24->7|25->8|27->10|27->10|27->10|28->11|29->12|29->12|29->12|30->13|30->13|30->13|30->13|30->13|30->13|30->13|30->13|31->14|31->14|31->14|32->15|33->16|33->16|33->16|33->16|34->17|34->17|35->18|35->18|36->19|37->20|38->21|39->22|40->23|42->25|42->25|42->25|43->26|43->26|43->26|43->26|48->31|49->32|49->32|49->32|51->34|51->34|51->34|52->35|52->35|52->35|52->35|52->35|53->36|53->36|53->36|54->37|54->37|55->38|55->38|55->38|56->39|56->39|56->39|57->40|58->41|58->41|58->41|59->42|59->42|59->42|59->42|60->43|61->44|61->44|61->44|61->44|62->45|62->45|62->45|63->46|63->46|63->46|64->47|64->47|64->47|64->47|64->47|64->47|65->48|65->48|65->48|65->48|65->48|65->48|65->48|66->49|66->49|66->49|67->50|67->50|67->50|67->50|67->50|67->50|68->51|68->51|68->51|68->51|68->51|68->51|68->51|69->52|70->53|71->54|71->54|71->54|72->55|72->55|72->55|73->56|73->56|73->56|73->56|73->56|74->57|74->57|74->57|75->58|75->58|75->58|76->59|76->59|77->60|77->60|77->60|78->61|78->61|78->61|79->62|79->62|79->62|80->63|80->63|80->63|81->64|82->65|82->65|82->65|83->66|83->66|84->67|85->68|86->69|87->70|88->71|89->72|90->73|91->74
                  -- GENERATED --
              */
          