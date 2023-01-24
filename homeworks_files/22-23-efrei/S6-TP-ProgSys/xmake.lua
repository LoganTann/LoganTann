add_rules("mode.debug", "mode.release")

local TPs = {
    ["TP1"] = {
        "_Exo1",
        "_Exo2",
        "_Exo3",
        "_Exo4",
        "_ExoFinal"
    }
}

for tpKey, tpContents in pairs(TPs) do
    for i, exo in pairs(tpContents) do
        target(tpKey..exo)
            set_kind("binary")
            set_languages("c18")
            add_files("src/"..tpKey.."_Commons/*.c")
            add_files("src/"..tpKey..exo.."/*.c")
    end
end

   
