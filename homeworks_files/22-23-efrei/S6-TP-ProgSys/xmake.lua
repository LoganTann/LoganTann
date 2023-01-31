add_rules("mode.debug", "mode.release")

local TPs = {
    ["TP1"] = {
        "Exo1",
        "Exo2",
        "Exo3",
        "Exo4",
        "ExoFinal"
    },
    ["TP2"] = {
        "Exo1"
    }
}

for tpKey, tpContents in pairs(TPs) do
    for i, exo in pairs(tpContents) do
        target(tpKey.."-"..exo)
            set_kind("binary")
            set_languages("c18")
            add_files("src/"..tpKey.."/Commons/*.c")
            add_files("src/"..tpKey.."/"..exo.."/*.c")
    end
end

   
