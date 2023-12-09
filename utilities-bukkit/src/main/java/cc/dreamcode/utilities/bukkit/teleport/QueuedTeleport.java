package cc.dreamcode.utilities.bukkit.teleport;

import cc.dreamcode.utilities.CountUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.HumanEntity;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;

@Data
@RequiredArgsConstructor
public class QueuedTeleport {

    private final Instant instant;
    private final Duration duration;
    private final boolean cancelOnMove;

    private final Consumer<HumanEntity> countdownNotice;
    private final Consumer<HumanEntity> movedNotice;
    private final Consumer<HumanEntity> alreadyInAction;
    private final Consumer<HumanEntity> taskAfter;

    public Duration getCountdown() {
        return CountUtil.getCountDown(this.instant, this.duration);
    }

}
