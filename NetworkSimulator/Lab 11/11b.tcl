set ns [new Simulator]

set ntrace [open prog4.tr w]
$ns trace-all $ntrace

set namfile [open prog4.nam w]
$ns namtrace-all-wireless $namfile 500 500

set topo [new Topography]

$topo load_flatgrid 500 500

$ns node-config -adhocRouting DSDV
$ns node-config -llType LL
$ns node-config -ifqType Queue/DropTail
$ns node-config -ifqLen 50
$ns node-config -macType Mac/802_11
$ns node-config -channelType Channel/WirelessChannel

$ns node-config -phyType Phy/WirelessPhy
$ns node-config -propType Propagation/TwoRayGround
$ns node-config -antType Antenna/OmniAntenna

$ns node-config -topoInstance $topo
$ns node-config -agentTrace ON
$ns node-config -routerTrace ON


create-god 4
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$n0 set X_ 250.0
$n0 set Y_ 250.0
$n0 set Z_ 0.0
$n1 set X_ 200.0
$n1 set Y_ 250.0
$n1 set Z_ 0.0
$n2 set X_ 250.0
$n2 set Y_ 250.0
$n2 set Z_ 0.0
$n3 set X_ 250.0
$n3 set Y_ 250.0
$n3 set Z_ 0.0


$ns at 0.0 "$n0 setdest 400.0 300.0 50.0"
$ns at 0.0 "$n1 setdest 50.0 100.0 20.0"
$ns at 0.0 "$n2 setdest 75.0 180.0 5.0"
$ns at 0.0 "$n3 setdest 100.0 100.0 25.0"

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n1 $sink0
$ns connect $tcp0 $sink0

set tcp1 [new Agent/TCP]
$ns attach-agent $n2 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n3 $sink1
$ns connect $tcp1 $sink1


set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $tcp0
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $tcp1

proc End {} {
global ns ntrace namfile
$ns flush-trace
close $namfile
close $ntrace
exec nam prog4.nam &
}

$ns at 0.0 "$cbr0 start"
$ns at 0.0 "$cbr1 start"
$ns at 10.0 "End"
$ns run

AWK Code
|
BEGIN{Num_of_pkts=0;} {
if ($1 == "r" && $3 == "1" && $4 == "AGT" && $7 == "tcp") {
Num_of_pkts = Num_of_pkts + $8;
}
}
END {
Throughput = Num_of_pkts * 8 / $2 /1000000;
printf("\n\n\tThroughput = %fbpms\n\n\n",Throughput);
}