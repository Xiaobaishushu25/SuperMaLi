package xbss

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.*
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns
import com.almasb.fxgl.entity.components.CollidableComponent
import com.almasb.fxgl.entity.components.IrremovableComponent
import com.almasb.fxgl.entity.components.ViewComponent
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import com.almasb.fxgl.physics.PhysicsComponent
import com.almasb.fxgl.physics.box2d.dynamics.BodyType
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import javafx.geometry.Point2D
import javafx.scene.image.Image
import javafx.scene.shape.Rectangle
import javafx.util.Duration
import xbss.components.*
import xbss.gameEnum.Direction
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-09-30-22:06
 * @version  1.0
 * @describe
 */
class SuperEntityFactory:EntityFactory {
    @Spawns("ground")
    fun newGround(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        return FXGL.entityBuilder(data)
            .type(GameType.GROUND)
            .bbox(BoundingShape.box(width,height))
            .with(PhysicsComponent())
            .collidable()
            .neverUpdated()
            .build()
    }
    @Spawns("gan")
    fun newGan(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        return FXGL.entityBuilder(data)
            .type(GameType.GAN)
            .bbox(BoundingShape.box(width,height))
            .with(PhysicsComponent())
//            .collidable()
            .neverUpdated()
            .build()
    }
    @Spawns("xin")
    fun newGro(data: SpawnData):Entity{
        return FXGL.entityBuilder(data)
            .type(GameType.GROUND)
            .viewWithBBox("map/img.png")
            .with(PhysicsComponent())
            .build()
    }
    @Spawns("mali")
    fun newMaLi(data: SpawnData):Entity{
        val physicsComponent = PhysicsComponent().apply {
            setBodyType(BodyType.DYNAMIC)
//            addGroundSensor(HitBox("GROUND_SENSOR", Point2D(16.0,38.0), BoundingShape.box(37.0,37.0)))
            addGroundSensor(HitBox("GROUND_SENSOR",  BoundingShape.box(37.0,37.0)))
            setFixtureDef(FixtureDef().friction(0.0f))
        }
        return FXGL.entityBuilder(data)
            .type(GameType.PLAYER)
//            .viewWithBBox("role/stand_R.png")
//            .bbox(HitBox(Point2D(10.0,0.0), BoundingShape.box(10.0, 17.0)))
//            .viewWithBBox("role/stand_R.png")
            .bbox(HitBox("head",Point2D(5.0,3.0), BoundingShape.box(18.0,8.0)))
            .bbox(HitBox("body",Point2D(5.0,11.0), BoundingShape.box(18.0,8.0)))
            .bbox(HitBox("foot",Point2D(25/2.0,19.0), BoundingShape.circle(2.0)))
            .with(physicsComponent)
            .with(MaLiComponent())
            .with(IrremovableComponent())
            .collidable()
            .build()
    }
    @Spawns("brick")
    fun newBrick(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        return FXGL.entityBuilder(data)
            .type(GameType.BRICK)
                //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
            .bbox(HitBox("bottom",Point2D(3.0,30.0), BoundingShape.box(30.0,7.0)))
            .bbox(HitBox("top",Point2D(0.0,0.0), BoundingShape.box(37.0,30.0)))
//            .viewWithBBox("map/brick.png")
            .with(SquareComponent())
            .with(EffectComponent())
            .with(PhysicsComponent())
            .collidable()
            .build()
    }
    @Spawns("what")
    fun newWhat(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        return FXGL.entityBuilder(data)
            .type(GameType.WHAT)
            //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
            .bbox(HitBox("bottom",Point2D(3.0,30.0), BoundingShape.box(30.0,7.0)))
            .bbox(HitBox("top",Point2D(0.0,0.0), BoundingShape.box(37.0,30.0)))
//            .viewWithBBox("map/brick.png")
            .with(SquareComponent())
            .with(PhysicsComponent())
            .with(EffectComponent())
            .collidable()
            .build()
    }
    @Spawns("pipe")
    fun newPipe(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        val zindex = data.get<Int>("zindex")
        if (zindex == 0){
            return FXGL.entityBuilder(data)
                .type(GameType.PIPE)
                //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
                .bbox(HitBox(BoundingShape.box(width,height)))
//            .viewWithBBox("map/brick.png")
                .with(PhysicsComponent())
                .zIndex(zindex)
                .collidable()
                .build()
        }else{
            return FXGL.entityBuilder(data)
                .type(GameType.PIPE)
                //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
//            .viewWithBBox("map/brick.png")
                .zIndex(zindex)
                .build()
        }
    }
    @Spawns("sea")
    fun newSea(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        val channel = AnimationChannel(FXGL.image("map/sea_anim.png"), Duration.seconds(0.3), 2)
        val at = AnimatedTexture(channel)
        return FXGL.entityBuilder(data)
            .type(GameType.PIPE)
            //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
            .view(at.loop())
            .bbox(HitBox(BoundingShape.box(width,height)))
//            .viewWithBBox("map/brick.png")
            .collidable()
            .build()
    }
    @Spawns("item")
    fun newItem(data: SpawnData):Entity{
        val physicsComponent = PhysicsComponent().apply {
            setBodyType(BodyType.DYNAMIC)
//            addGroundSensor(HitBox("GROUND_SENSOR", Point2D(16.0,38.0), BoundingShape.box(37.0,37.0)))
            addGroundSensor(HitBox("GROUND_SENSOR",  BoundingShape.box(37.0,37.0)))
            setFixtureDef(FixtureDef().friction(0.0f))
        }
        return FXGL.entityBuilder(data)
            .type(GameType.ITEM)
            //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
//            .bbox(HitBox(BoundingShape.box(width,height)))
            .bbox(HitBox(Point2D(0.0,0.0),BoundingShape.box(37.0,20.0)))
            .bbox(HitBox(Point2D(37/2.0,(37-20)/2.0),BoundingShape.circle(8.0)))
            .with(EffectComponent())
            .with(ItemComponent())
            .with(OffscreenCleanComponent())
            .collidable()
            .build()
//        val width: Double = data.get<Double>("width")
//        val height: Double = data.get<Double>("height")
    }
    @Spawns("itemWithPhy")
    fun newItemWithPhy(data: SpawnData):Entity{
        val physicsComponent = PhysicsComponent().apply {
            setBodyType(BodyType.DYNAMIC)
            addGroundSensor(HitBox("GROUND_SENSOR",  BoundingShape.box(37.0,37.0)))
            setFixtureDef(FixtureDef().friction(0.0f))
        }
        return FXGL.entityBuilder(data)
            .type(GameType.ITEM)
            //底部宽度要小一点，防止脸贴到侧边也会触发碰撞
//            .bbox(HitBox(BoundingShape.box(width,height)))
            .bbox(HitBox(BoundingShape.box(37.0,20.0)))
            .bbox(HitBox(Point2D(28/2.0,(37-6)/2.0),BoundingShape.circle(8.0)))
//            .viewWithBBox("map/brick.png")
            .with(physicsComponent)
            .with(ItemWithPhyComponent())
            .with(OffscreenCleanComponent())
            .collidable()
            .build()
//        val width: Double = data.get<Double>("width")
//        val height: Double = data.get<Double>("height")
    }
    @Spawns("bullet")
    fun newBullet(data: SpawnData):Entity{
        FXGL.play("normalFire.wav")
        val dir = data.get<Point2D>("dir")
        return FXGL.entityBuilder(data)
            .type(GameType.BULLET)
            .viewWithBBox("bullet.png")
            .with(ProjectileComponent(dir,Config.BULLET_SPEED.toDouble()))
            .with(BulletComponent())
            .with(OffscreenCleanComponent())
            .collidable()
            .build()
    }
    @Spawns("explode")
    fun newExplode(data: SpawnData):Entity{
        FXGL.play("normalBomb.wav")
        val ac = AnimationChannel(FXGL.image("explode.png"), Config.EXPLODE_TIME, 9)
        val at = AnimatedTexture(ac)
        return FXGL.entityBuilder(data)
            .view(at.play())
            .with(ExpireCleanComponent(Config.EXPLODE_TIME))
            .build()
    }
    @Spawns("trigger")
    fun newTrigger(data: SpawnData):Entity{
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        return  FXGL.entityBuilder(data)
            .type(GameType.TRIGGER)
            .bbox(BoundingShape.box(width,height))
            .neverUpdated()
            .collidable()
            .build()
    }
    @Spawns("fun")
    fun newFun(data: SpawnData):Entity{
        val runChannel = AnimationChannel(ArrayList<Image>() .apply {
            add(FXGL.image("role/npc/fungus1.png"))
            add(FXGL.image("role/npc/fungus2.png"))
        }
            , Duration.seconds(0.3),2)
        val at = AnimatedTexture(runChannel)
        val physicsComponent = PhysicsComponent().apply {
            setBodyType(BodyType.DYNAMIC)
            addGroundSensor(HitBox("GROUND_SENSOR",  BoundingShape.box(37.0,37.0)))
            setFixtureDef(FixtureDef().friction(0.0f))
//            velocityX = -Config.NPC_MOVE_SPEED.toDouble()
        }
        val liftComponent = LiftComponent().apply {
            isGoingRight = true
            this.xAxisDistanceDuration(7*37.0, Duration.seconds(5.0))
        }
        return FXGL.entityBuilder(data)
            .type(GameType.NPC)
            .view(at.loop())
            .bbox(HitBox(Point2D(0.0,0.0),BoundingShape.box(37.0,20.0)))
//            .bbox(HitBox(Point2D(37/2.0-10,(37-20)/2.0),BoundingShape.circle(8.0)))
            .bbox(HitBox(Point2D(37/2.0-10,37-20.0),BoundingShape.circle(10.0)))
//            .viewWithBBox(at.loop())
            .with(physicsComponent)
            .with(NpcComponent())
//            .with(liftComponent)
            .with(EffectComponent())
//            .with(OffscreenCleanComponent())
            .collidable()
            .build()
    }
    @Spawns("flower")
    fun newFlower(data: SpawnData):Entity{
        val runChannel = AnimationChannel(ArrayList<Image>() .apply {
            add(FXGL.image("role/npc/flower1.png"))
            add(FXGL.image("role/npc/flower2.png"))
        } , Duration.seconds(0.4),2)
        val at = AnimatedTexture(runChannel)
        val liftComponent = LiftComponent().apply {
            isGoingUp = true
            yAxisDistanceDuration(55.0, Duration.seconds(5.0))
        }
        val width: Double = data.get<Double>("width")
        val height: Double = data.get<Double>("height")
        return  FXGL.entityBuilder(data)
            .type(GameType.FLOWER)
            .view(at.loop())
            .bbox(BoundingShape.box(width,height))
            .with(liftComponent)
            .collidable()
            .build()
    }
}