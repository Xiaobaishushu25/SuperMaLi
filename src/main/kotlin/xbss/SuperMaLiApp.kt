package xbss

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.app.scene.Viewport
import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.input.UserAction
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import xbss.collisions.*
import xbss.components.MaLiComponent
import xbss.gameEnum.GameType


/**
 * @author  Xbss
 * @create 2022-09-30-22:06
 * @version  1.0
 * @describe
 */
class SuperMaLiApp :GameApplication(){
    private lateinit var player: Entity
    override fun initSettings(settings: GameSettings?) {
        val notNullSettings = settings!!
        notNullSettings.apply {
            width = 35*Config.CELL_SIZE
            height = 18*37
            title = "SuperMaLi"
            version = "0.1"
            isDeveloperMenuEnabled = true
//            appIcon = "icon.png"
//            defaultCursor = CursorInfo("cursor.png",0.0,0.0)
        }
    }

    override fun onPreInit() {
//        FXGL.getSettings().globalMusicVolume = 0.5
        FXGL.loopBGM("bgmmusic.wav");
    }

    override fun initGame() {
        FXGL.getGameScene().setBackgroundColor(Color.valueOf("#63A5DE"))
        FXGL.getGameWorld().addEntityFactory(SuperEntityFactory())
        FXGL.setLevelFromMap("one.tmx")
        player = FXGL.spawn("mali", 100.0, 50.0)
//        player = FXGL.spawn("player", 100.0, 50.0)
//        FXGL.spawn("wall", 100.0, 591-37.0)
        FXGL.spawn("xin", 100.0, 300.0)
        FXGL.spawn("xin", 100+24.0, 300.0)
        FXGL.spawn("xin", 100+24.0*2, 300.0)
        FXGL.spawn("xin", 100+24.0*3, 300.0)
        FXGL.spawn("xin", 100+24.0*4, 300.0)
        FXGL.spawn("xin", 100+24.0*5, 300.0)
        FXGL.spawn("xin", 100+24.0*6, 300.0)
        FXGL.spawn("xin", 100+24.0*7, 300.0)
        FXGL.spawn("xin", 100+24.0*8, 300.0)
        FXGL.spawn("xin", 100+24.0*9, 300.0)
        FXGL.spawn("xin", 100+24.0*10, 300.0)
        FXGL.spawn("xin", 100+24.0*11, 300.0)
        FXGL.spawn("xin", 100+24.0*12, 300.0)
        FXGL.spawn("xin", 100+24.0*13, 300.0)
        FXGL.spawn("xin", 100+24.0*14, 300.0)
        FXGL.spawn("xin", 100+24.0*15, 300.0)
        FXGL.spawn("xin", 100+24.0*16, 300.0)
        val viewport: Viewport = FXGL.getGameScene().viewport
        viewport.setBounds(-1500, 0, 250 * 70, FXGL.getAppHeight())
        viewport.bindToEntity(player, FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0)
        viewport.isLazy = true
    }

    override fun initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0.0, 760.0)
        FXGL.getPhysicsWorld().addCollisionHandler(PlayerAndBrick())
        FXGL.getPhysicsWorld().addCollisionHandler(PlayerAndWhat())
        FXGL.getPhysicsWorld().addCollisionHandler(PlayerAndNpc())
        FXGL.getPhysicsWorld().addCollisionHandler(PlayerAndItem())
        FXGL.getPhysicsWorld().addCollisionHandler(ItemAndPipe())
        val bulletAndGround = BulletAndGround()
        FXGL.getPhysicsWorld().addCollisionHandler(bulletAndGround)
        FXGL.getPhysicsWorld().addCollisionHandler(bulletAndGround.copyFor(GameType.BULLET,GameType.BRICK))
        FXGL.getPhysicsWorld().addCollisionHandler(bulletAndGround.copyFor(GameType.BULLET,GameType.WHAT))
        val bulletAndNpc = BulletAndNpc()
        FXGL.getPhysicsWorld().addCollisionHandler(bulletAndNpc)
        FXGL.getPhysicsWorld().addCollisionHandler(bulletAndNpc.copyFor(GameType.BULLET,GameType.PIPE))
        FXGL.getPhysicsWorld().addCollisionHandler(NpcAndPipe())
        FXGL.getPhysicsWorld().addCollisionHandler(PlayerAndTrigger())
    }

    override fun initInput() {
//        FXGL.onKey(KeyCode.UP){player.getComponent(MaLiComponent::class.java).apply { moveUp() }}
//        FXGL.onKey(KeyCode.DOWN){player.getComponent(MaLiComponent::class.java).apply { moveDown()}}
//        FXGL.onKey(KeyCode.LEFT){player.getComponent(MaLiComponent::class.java).apply { moveLeft() }}
//        FXGL.onKey(KeyCode.RIGHT){player.getComponent(MaLiComponent::class.java).apply { moveRight() }}
        FXGL.onKey(KeyCode.SPACE){player.getComponent(MaLiComponent::class.java).shoot()}
        FXGL.getInput().addAction(object :UserAction("Left"){
            override fun onAction() {
                player.getComponent(MaLiComponent::class.java).moveLeft()
            }

            override fun onActionEnd() {
                player.getComponent(MaLiComponent::class.java).stop()
            }
        },KeyCode.LEFT)
        FXGL.getInput().addAction(object :UserAction("Right"){
            override fun onAction() {
                player.getComponent(MaLiComponent::class.java).moveRight()
            }
            override fun onActionEnd() {
                player.getComponent(MaLiComponent::class.java).stop()
            }
        },KeyCode.RIGHT)
        FXGL.getInput().addAction(object :UserAction("Jump"){
            override fun onActionBegin() {
//                FXGL.play("new.wav")
                FXGL.play("newjump.wav")
//                FXGL.play("jump.wav")
                player.getComponent(MaLiComponent::class.java).moveUp()
            }
        },KeyCode.UP)
//        FXGL.getInput().addAction(object : UserAction("Left") {
//            override fun onAction() {
//                player.getComponent(PlayerComponent::class.java).left()
//            }
//
//            override fun onActionEnd() {
//                player.getComponent(PlayerComponent::class.java).stop()
//            }
//        }, KeyCode.A, VirtualButton.LEFT)
//
//        FXGL.getInput().addAction(object : UserAction("Right") {
//            override fun onAction() {
//                player.getComponent(PlayerComponent::class.java).right()
//            }
//
//            override fun onActionEnd() {
//                player.getComponent(PlayerComponent::class.java).stop()
//            }
//        }, KeyCode.D, VirtualButton.RIGHT)
//
//        FXGL.getInput().addAction(object : UserAction("Jump") {
//            override fun onActionBegin() {
//                player.getComponent(PlayerComponent::class.java).jump()
//            }
//        }, KeyCode.W, VirtualButton.A)

    }
}

fun main(args: Array<String>) {
    GameApplication.launch(SuperMaLiApp::class.java,args)
}