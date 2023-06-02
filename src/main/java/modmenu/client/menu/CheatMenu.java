package modmenu.client.menu;

import modmenu.ModMenu;
import modmenu.server.playerdata.PlayerData;
import modmenu.utils.AchievementsUnlocker;
import necesse.engine.commands.CommandsManager;
import necesse.engine.commands.ParsedCommand;
import necesse.engine.control.InputEvent;
import necesse.engine.network.client.Client;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.engine.registries.BuffRegistry;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.FormManager;
import necesse.gfx.forms.components.*;
import necesse.gfx.forms.components.lists.FormStringList;
import necesse.gfx.gameFont.FontOptions;


public class CheatMenu extends Form {

    private final FormManager formManager;
    private final Client client;
    public boolean shown;

    public CurrentMenu currentMenu = CurrentMenu.Main;

    public CheatMenu(final FormManager formManager, final Client client) {
        super("Cheats", 500, 330);
        this.setPosition(700, 150);

        this.formManager = formManager;
        this.client = client;
    }

    @Override
    public void handleInputEvent(final InputEvent event, final TickManager tickManager, final PlayerMob perspective) {
        super.handleInputEvent(event, tickManager, perspective);

        if (event.isKeyboardEvent() && event.getID() == 256) {
            this.shown = false;
            this.formManager.removeComponent(this);
        }
    }

    public void initMenu() {
        this.currentMenu = CurrentMenu.Main;

        for (int i = 1; i < CurrentMenu.values().length; i++) {
            final CurrentMenu menu = CurrentMenu.values()[i];

            this.addComponent(
                    new FormTextButton(menu.name, menu.description, 5, 5 + (12 * ((i - 1) * 3)), 100)
                            .onClicked(e -> {
                                this.clearComponents();
                                this.currentMenu = menu;
                                this.initPane();
                            })
            );
        }
    }

    public void initPane() {
        this.addComponent(new FormTextButton("<", "Back", 460, 5, 30).onClicked(e -> {
            this.clearComponents();
            this.initMenu();
        }));

        switch (this.currentMenu) {
            case Player:
                this.initPlayerMenu();
                break;
            case Server:
                this.initServerMenu();
                break;
            case Misc:
                this.initMiscMenu();
                break;
        }
    }

    public void initPlayerMenu() {
        final PlayerData playerData = ModMenu.instance.playerDataManger.get(client.getPlayer());

        final FormCheckBox godmodeCheckbox = new FormCheckBox("Godmode", 5, 10);
        final FormCheckBox maxhealthbuffCheckBox = new FormCheckBox("Max Health x10", 5, 30);
        final FormCheckBox damageMultiplierCheckBox = new FormCheckBox("Damage x5", 5, 50);
        final FormCheckBox atsbuffCheckBox = new FormCheckBox("Super Attackspeed", 5, 70);
        final FormCheckBox infammobuffCheckBox = new FormCheckBox("Infinite Ammo", 5, 90);
        final FormCheckBox manymobsbuffCheckBox = new FormCheckBox("Many Mobs", 5, 110);
        final FormCheckBox superminingbuffCheckBox = new FormCheckBox("Super Mining", 5, 130);
        final FormCheckBox superpickupbuffCheckBox = new FormCheckBox("Super Pickup Range", 5, 150);
        final FormCheckBox unlimitedSummonsCheckBox = new FormCheckBox("Unlimited Summons", 5, 170);
        final FormCheckBox supersummonsbuffCheckBox = new FormCheckBox("Super Summons", 5, 190);
        final FormCheckBox insiviblebuffCheckBox = new FormCheckBox("Invisible", 5, 210);
        final FormSlider speedTextBox = new FormSlider("Speed Boost", 5, 230, ModMenu.instance.clientSideStash.speed, 10, 2000, 200, new FontOptions(10));

        necesse.entity.mobs.buffs.staticBuffs.Buff buffSums = BuffRegistry.getBuff("unlimitedsummonsbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffDamage = BuffRegistry.getBuff("dmgmultbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffATS = BuffRegistry.getBuff("atsbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffInv = BuffRegistry.getBuff("insiviblebuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffManyMobs = BuffRegistry.getBuff("manymobsbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffSuperMining = BuffRegistry.getBuff("superminingbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffSuperPickup = BuffRegistry.getBuff("superpickupbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffSuperSummons = BuffRegistry.getBuff("supersummonsbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffMaxHealth = BuffRegistry.getBuff("maxhealthbuff");
        necesse.entity.mobs.buffs.staticBuffs.Buff buffInfAmmo = BuffRegistry.getBuff("infammobuff");

        speedTextBox.onChanged(e -> {
            ModMenu.instance.clientSideStash.speed = speedTextBox.getValue();
            client.getPlayer().setSpeed(35.0F + speedTextBox.getValue());
        });

        unlimitedSummonsCheckBox.onClicked(e -> {
            ActiveBuff ab;

            System.out.println("unlimitedSumms: " + ModMenu.instance.clientSideStash.unlimitedsummonsbuff);
            if(ModMenu.instance.clientSideStash.unlimitedsummonsbuff) {
                ModMenu.instance.clientSideStash.unlimitedsummonsbuff = false;
                client.getPlayer().buffManager.removeBuff(buffSums, true);
            } else {
                ModMenu.instance.clientSideStash.unlimitedsummonsbuff = true;
                ab = new ActiveBuff(buffSums, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        damageMultiplierCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.dmgmultbuff = false;
                client.getPlayer().buffManager.removeBuff(buffDamage, true);
            } else {
                ModMenu.instance.clientSideStash.dmgmultbuff = true;
                ab = new ActiveBuff(buffDamage, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        maxhealthbuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.maxhealthbuff = false;
                client.getPlayer().buffManager.removeBuff(buffMaxHealth, true);
            } else {
                ModMenu.instance.clientSideStash.maxhealthbuff = true;
                ab = new ActiveBuff(buffMaxHealth, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        atsbuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.atsbuff = false;
                client.getPlayer().buffManager.removeBuff(buffATS, true);
            } else {
                ModMenu.instance.clientSideStash.atsbuff = true;
                ab = new ActiveBuff(buffATS, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        insiviblebuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.insiviblebuff = false;
                client.getPlayer().buffManager.removeBuff(buffInv, true);
            } else {
                ModMenu.instance.clientSideStash.insiviblebuff = true;
                ab = new ActiveBuff(buffInv, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        manymobsbuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.manymobsbuff = false;
                client.getPlayer().buffManager.removeBuff(buffManyMobs, true);
            } else {
                ModMenu.instance.clientSideStash.manymobsbuff = true;
                ab = new ActiveBuff(buffManyMobs, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        superminingbuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.superminingbuff = false;
                client.getPlayer().buffManager.removeBuff(buffSuperMining, true);
            } else {
                ModMenu.instance.clientSideStash.superminingbuff = true;
                ab = new ActiveBuff(buffSuperMining, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        superpickupbuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.superpickupbuff = false;
                client.getPlayer().buffManager.removeBuff(buffSuperPickup, true);
            } else {
                ModMenu.instance.clientSideStash.superpickupbuff = true;
                ab = new ActiveBuff(buffSuperPickup, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        supersummonsbuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.supersummonsbuff = false;
                client.getPlayer().buffManager.removeBuff(buffSuperSummons, true);
            } else {
                ModMenu.instance.clientSideStash.supersummonsbuff = true;
                ab = new ActiveBuff(buffSuperSummons, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });

        infammobuffCheckBox.onClicked(e -> {
            ActiveBuff ab;

            if(!e.from.checked) {
                ModMenu.instance.clientSideStash.infammobuff = false;
                client.getPlayer().buffManager.removeBuff(buffInfAmmo, true);
            } else {
                ModMenu.instance.clientSideStash.infammobuff = true;
                ab = new ActiveBuff(buffInfAmmo, client.getPlayer(), 999999999, null); // initiate and configure buff
                client.getPlayer().buffManager.addBuff(ab, true); // set buff
            }
        });


        godmodeCheckbox.onClicked(e -> {
            ModMenu.instance.clientSideStash.godMode = !ModMenu.instance.clientSideStash.godMode;
        });

        this.addComponent(godmodeCheckbox);
        this.addComponent(speedTextBox);
        this.addComponent(unlimitedSummonsCheckBox);
        this.addComponent(damageMultiplierCheckBox);
        this.addComponent(atsbuffCheckBox);
        this.addComponent(insiviblebuffCheckBox);
        this.addComponent(manymobsbuffCheckBox);
        this.addComponent(superminingbuffCheckBox);
        this.addComponent(superpickupbuffCheckBox);
        this.addComponent(supersummonsbuffCheckBox);
        this.addComponent(maxhealthbuffCheckBox);
        this.addComponent(infammobuffCheckBox);

        godmodeCheckbox.checked = ModMenu.instance.clientSideStash.godMode;
        unlimitedSummonsCheckBox.checked = ModMenu.instance.clientSideStash.unlimitedsummonsbuff;
        damageMultiplierCheckBox.checked = ModMenu.instance.clientSideStash.dmgmultbuff;
        atsbuffCheckBox.checked = ModMenu.instance.clientSideStash.atsbuff;
        insiviblebuffCheckBox.checked = ModMenu.instance.clientSideStash.insiviblebuff;
        manymobsbuffCheckBox.checked = ModMenu.instance.clientSideStash.manymobsbuff;
        superminingbuffCheckBox.checked = ModMenu.instance.clientSideStash.superminingbuff;
        superpickupbuffCheckBox.checked = ModMenu.instance.clientSideStash.superpickupbuff;
        supersummonsbuffCheckBox.checked = ModMenu.instance.clientSideStash.supersummonsbuff;
        maxhealthbuffCheckBox.checked = ModMenu.instance.clientSideStash.maxhealthbuff;
        infammobuffCheckBox.checked = ModMenu.instance.clientSideStash.infammobuff;
    }

    public void initServerMenu() {

    }

    public void initMiscMenu() {
        this.addComponent(new FormTextButton("Unlock steam", "This Button will unlock all steam achievements!", 5, 5, 150).onClicked(e -> {
            AchievementsUnlocker.unlock();
        }));
    }

}
