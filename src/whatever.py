
# Online Python - IDE, Editor, Compiler, Interpreter


txt = """assets/minecraft/sounds/music/game/yakusoku.ogg
assets/minecraft/sounds/music/game/wet_hands.ogg
assets/minecraft/sounds/music/game/wending.ogg
assets/minecraft/sounds/music/game/water/shuniji.ogg
assets/minecraft/sounds/music/game/water/dragon_fish.ogg
assets/minecraft/sounds/music/game/water/axolotl.ogg
assets/minecraft/sounds/music/game/watcher.ogg
assets/minecraft/sounds/music/game/sweden.ogg
assets/minecraft/sounds/music/game/swamp/labyrinthine.ogg
assets/minecraft/sounds/music/game/swamp/firebugs.ogg
assets/minecraft/sounds/music/game/swamp/aerie.ogg
assets/minecraft/sounds/music/game/subwoofer_lullaby.ogg
assets/minecraft/sounds/music/game/stand_tall.ogg
assets/minecraft/sounds/music/game/puzzlebox.ogg
assets/minecraft/sounds/music/game/pokopoko.ogg
assets/minecraft/sounds/music/game/oxygene.ogg
assets/minecraft/sounds/music/game/os_piano.ogg
assets/minecraft/sounds/music/game/one_more_day.ogg
assets/minecraft/sounds/music/game/nether/warmth.ogg
assets/minecraft/sounds/music/game/nether/soulsand_valley/so_below.ogg
assets/minecraft/sounds/music/game/nether/nether_wastes/rubedo.ogg
assets/minecraft/sounds/music/game/nether/dead_voxel.ogg
assets/minecraft/sounds/music/game/nether/crimson_forest/chrysopoeia.ogg
assets/minecraft/sounds/music/game/nether/concrete_halls.ogg
assets/minecraft/sounds/music/game/nether/ballad_of_the_cats.ogg
assets/minecraft/sounds/music/game/minecraft.ogg
assets/minecraft/sounds/music/game/mice_on_venus.ogg
assets/minecraft/sounds/music/game/living_mice.ogg
assets/minecraft/sounds/music/game/lilypad.ogg
assets/minecraft/sounds/music/game/left_to_bloom.ogg
assets/minecraft/sounds/music/game/komorebi.ogg
assets/minecraft/sounds/music/game/key.ogg
assets/minecraft/sounds/music/game/infinite_amethyst.ogg
assets/minecraft/sounds/music/game/haggstrom.ogg
assets/minecraft/sounds/music/game/floating_dream.ogg
assets/minecraft/sounds/music/game/fireflies.ogg
assets/minecraft/sounds/music/game/featherfall.ogg
assets/minecraft/sounds/music/game/endless.ogg
assets/minecraft/sounds/music/game/end/the_end.ogg
assets/minecraft/sounds/music/game/end/boss.ogg
assets/minecraft/sounds/music/game/end/alpha.ogg
assets/minecraft/sounds/music/game/eld_unknown.ogg
assets/minecraft/sounds/music/game/echo_in_the_wind.ogg
assets/minecraft/sounds/music/game/dry_hands.ogg
assets/minecraft/sounds/music/game/deeper.ogg
assets/minecraft/sounds/music/game/danny.ogg
assets/minecraft/sounds/music/game/crescent_dunes.ogg
assets/minecraft/sounds/music/game/creative/taswell.ogg
assets/minecraft/sounds/music/game/creative/haunt_muskie.ogg
assets/minecraft/sounds/music/game/creative/dreiton.ogg
assets/minecraft/sounds/music/game/creative/blind_spots.ogg
assets/minecraft/sounds/music/game/creative/biome_fest.ogg
assets/minecraft/sounds/music/game/creative/aria_math.ogg
assets/minecraft/sounds/music/game/comforting_memories.ogg
assets/minecraft/sounds/music/game/clark.ogg
assets/minecraft/sounds/music/game/bromeliad.ogg
assets/minecraft/sounds/music/game/broken_clocks.ogg
assets/minecraft/sounds/music/game/below_and_above.ogg
assets/minecraft/sounds/music/game/ancestry.ogg
assets/minecraft/sounds/music/game/an_ordinary_day.ogg
assets/minecraft/sounds/music/game/a_familiar_room.ogg
"""

def outputJson(a, b):
    return (
    '"music.'+ b +'": {\n'
    '    "sounds": [\n'
    '    {\n'
    '        "name": "minecraft:music/'+ a +'",\n'
    '        "stream": true\n'
    '    }\n'
    '    ]\n'
    '},\n')

def outputReg(a):
    return f'public static final SoundEvent MUSIC_{a.upper()} = registerSoundOfVanilla("music.{a}");'

arr = txt.splitlines()



for item in arr:
    item = item[:-4]
    item2 = item.split("music/")[-1]
    item3 = item.split("/")[-1]
    print(outputJson(item2, item3))

print("________")

for item in arr:
    item = item[:-4]
    item = item.split("/")[-1]
    print(outputReg(item))
