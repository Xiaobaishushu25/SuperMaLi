package xbss.components

import com.almasb.fxgl.entity.component.Component

/**
 * @author  Xbss
 * @create 2022-10-02-17:21
 * @version  1.0
 * @describe
 */
class SquareComponent:Component() {
    var money = 0
    override fun onAdded() {
        money = entity.getInt("money")
    }
    fun minus(){
        money -= 1
    }
}