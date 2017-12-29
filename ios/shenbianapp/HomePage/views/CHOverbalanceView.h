//
//  CHKindView.h
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017 . All rights reserved.
//

typedef void (^SeeAllCategory)(void);
typedef void(^TapInOverBalence)(NSString *serviceId);

#import <UIKit/UIKit.h>


@interface CHOverbalanceView : UIView
@property(nonatomic,strong)NSArray *overBablanceList;
@property(nonatomic,copy)SeeAllCategory seeAllCategory;
@property(nonatomic,copy)TapInOverBalence tapInOverBalence;
@end
